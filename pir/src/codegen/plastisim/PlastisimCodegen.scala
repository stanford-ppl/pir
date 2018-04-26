package pir.codegen

import pir.node._
import spade.node._
import prism.util._

import prism.collection.mutable._

trait PlastisimCodegen extends PIRCodegen with FileManager {
  import pirmeta._
  import spademeta._

  type Node = ContextEnable
  type Link = linkGroup.V

  lazy val PLASTISIM_HOME = sys.env.get("PLASTISIM_HOME")

  def cumap = pirMap.right.get.cumap
  lazy val topParam = compiler.arch.topParam.asInstanceOf[DynamicMeshTopParam]

  override def runPass = {
    super.runPass // traverse dataflow graph and call emitNode on each node
    linkGroup.values.toSet.foreach { link => emitLink(link) }
  }

  override def finPass = {
    super.finPass
    val relativePath = s"configs/gen/${compiler}/${fileName}" 
    PLASTISIM_HOME.fold {
      warn(s"Specify PLASTISIM_HOME environmental variable to generate to PLASTISIM_HOME/${relativePath}")
    } { PLASTISIM_HOME =>
      val genPath = s"$PLASTISIM_HOME/${relativePath}"
      mkdir(dirName(genPath))
      copyFile(outputPath, genPath)
    }
  }

  override def emitNode(n:N) = n match {
    case n:Node => emitNetworkNode(n)
    case n => super.visitNode(n)
  }

  def emitNetworkNode(n:ContextEnable):Unit
  def emitLink(n:Link):Unit

  def srcOf(mem:Memory) = dbgblk(s"srcOf(${quote(mem)})") {
    def visitFunc(n:PIRNode) = visitGlobalIn(n).filterNot {
      case n:Memory => true
      case _ => false
    }
    mem.collect[Node](visitFunc=visitFunc _)
  }

  def srcsOf(n:Link) = {
    n.flatMap { mem => srcOf(mem) }
  }

  def dstOf(mem:Memory) = dbgblk(s"dstOf(${quote(mem)})"){
    def visitFunc(n:PIRNode) = visitGlobalOut(n).filterNot {
      case n:Memory => true
      case n:NotFull => true
      case _ => false
    }
    mem.collect[Node](visitFunc=visitFunc _)
  }

  def dstsOf(n:Link) = {
    n.flatMap { mem => dstOf(mem) }
  }

  def assertUnify[A,B](list:Iterable[A],info:String)(lambda:A => B):B = {
    val res = list.map(lambda)
    assert(res.toSet.size==1, s"$list doesnt have the same $info = $res")
    res.head
  }

  def inlinksOf(n:Node) = {
    val reads = n.collectOutTillMem[LocalLoad]() //reads enabled by this contextEnable
    val links = reads.groupBy { read =>
      val mem::Nil = memsOf(read) // All mems should belongs to the same linkGroup
      linkGroup(mem)
    }
    links.map { case (link, reads) =>
      val iter = assertUnify(reads, "iter") { read => itersOf(read) }
      val bufferSize = assertUnify(reads, "bufferSize") { read => val mem::Nil = memsOf(read); bufferSizeOf(mem) }
      (link, iter, bufferSize)
    }
  }

  def outlinksOf(n:Node) = {
    val writes = n.collectOutTillMem[LocalStore]() // writes enabled by this contextEnable
    val links = writes.groupBy { write =>
      val mem::Nil = memsOf(write) // All mems should belongs to the same linkGroup
      linkGroup(mem)
    }
    links.toSeq.map { case (link, writers) =>
      val iter = assertUnify(writers, "iter") { writer => itersOf(writer) }
      (link, iter)
    }
  }

  // Convert coordinate to linear index
  def addrOf(sn:SNode):Option[Int] = {
    import topParam._
    indexOf.get(sn).map { case List(xx,y) =>
      val x = xx + 1 // get ride of negative x coordinate
      val idx = y * (numCols + 2) + x
      dbg(s"$sn: coord = ($x, $y) idx = $idx")
      idx
    }
  }

  def addrOf(node:Node):Option[Int] = {
    val cuP = globalOf(node).get
    val cuS = cumap.mappedValue(cuP)
    addrOf(cuS)
  }

  def isStaticLink(src:Node, dst:Node):Boolean = {
    val srcCUP = globalOf(src).get
    val dstCUP = globalOf(dst).get
    val isStatic = srcCUP == dstCUP
    dbg(s"isStatic($src($srcCUP), $dst($dstCUP)) = $isStatic")
    isStatic
  }

  def isStaticLink(n:Link):Boolean = {
    val srcs = srcsOf(n)
    val dsts = dstsOf(n)
    val pairs = srcs.flatMap{ src => dsts.map { dst => (src, dst) } }
    assertUnify(pairs, "isStatic"){ case (src, dst) => isStaticLink(src, dst) }
  }

  def bufferSizeOf(memP:Memory):Int = {
    val cuP = globalOf(memP).get
    val cuS = cumap(cuP).head
    cuS match {
      case cuS:MC =>
        val isLoad = isLoadFringe(cuP)
        val isStore = isStoreFringe(cuP)
        memP match {
          case memP:StreamOut if memP.field == "size" & isLoad =>
            cuS.param.rSizeFifoParam.size
          case memP:StreamOut if memP.field == "offset" & isLoad =>
            cuS.param.rOffsetFifoParam.size
          case memP:StreamOut if memP.field == "size" & isStore =>
            cuS.param.wSizeFifoParam.size
          case memP:StreamOut if memP.field == "offset" & isStore =>
            cuS.param.wOffsetFifoParam.size
          case memP:StreamOut if memP.field == "data" & isStore=>
            if (isWord(memP)) cuS.param.sDataFifoParam.size
            else if (isVector(memP)) cuS.param.vDataFifoParam.size
            else throw PIRException(s"Unsupported dram data type ${pinTypeOf(memP)}")
          case memP:StreamIn if memP.field == "data" & isLoad => 1
          case memP:StreamIn if memP.field == "data" & isStore => 1
        }
      case cuS:CU =>
        memP match {
          case memP if isFIFO(memP) =>
            memP match {
              case memP if isBit(memP) => cuS.param.controlFifoParam.size
              case memP if isWord(memP) => cuS.param.scalarFifoParam.size
              case memP if isVector(memP) => cuS.param.vectorFifoParam.size
            }
          case memP:pir.node.SRAM => cuS.param.sramParam.depth
          case memP if isReg(memP) => cuS.param.scalarFifoParam.size
        }
      case cuS:spade.node.ArgFringe => 1
    }

  }

  def linkTp(n:Memory):String = n match {
    case n if isBit(n) => "ctrl"
    case n if isWord(n) => "scal"
    case n if isVector(n) => "vec"
  }

  def linkTp(n:Link):String = assertUnify(n, "linkTp")(linkTp)

  def latencyOf(n:Node) = {
    globalOf(n).get match {
      case cuP:DramFringe => 100
      case cuP =>
        val cuS = cumap.mappedValue(cuP)
        val stages = cuS.collectDown[Stage]()
        Math.max(stages.size, 1)
    }
  }

  def staticLatencyOf(src:Node, dst:Node) = {
    1 //TODO: change for static network
  }

  override def quote(n:Any):String = n match {
    case n:Set[_] if n.forall(_.isInstanceOf[Memory]) => n.mkString("_") // Link
    case n => super.quote(n)
  }

}
