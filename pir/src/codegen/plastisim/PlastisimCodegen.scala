package pir.codegen

import pir.node._
import spade.node._

import prism.collection.mutable._

trait PlastisimCodegen extends PIRCodegen {
  import pirmeta._
  import spademeta._

  type Link = LocalStore

  def cumap = pirMap.right.get.cumap
  lazy val topParam = compiler.arch.topParam.asInstanceOf[DynamicMeshTopParam]

  // Convert coordinate to linear index
  def addrOf(sn:SNode) = {
    import topParam._
    var List(x,y) = indexOf(sn)
    x += 1 // get ride of negative x coordinate
    val idx = y * (numCols + 2) + x
    dbg(s"$sn: coord = ($x, $y) idx = $idx")
    idx
  }

  def isStaticLink(mem:Memory, srcCUP:GlobalContainer, dstCUP:GlobalContainer) = (mem, srcCUP, dstCUP) match {
    case (mem, srcCUP:pir.node.ArgFringe, dstCUP) => true
    case (mem, srcCUP, dstCUP:pir.node.ArgFringe) => true
    case (mem, srcCUP, dstCUP) if isPMU(srcCUP) && isPMU(dstCUP) => true
    case _ => false
  }

  def bufferSizeOf(n:Link) = {
    val memP = memsOf(n).head
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
            if (isWord(n)) cuS.param.sDataFifoParam.size
            else if (isVector(n)) cuS.param.vDataFifoParam.size
            else throw PIRException(s"Unsupported dram data type ${pinTypeOf(n)}")
        }
      case cuS:CU =>
        memP match {
          case memP:pir.node.RetimingFIFO =>
            n match {
              case n if isBit(n) => cuS.param.controlFifoParam.size
              case n if isWord(n) => cuS.param.scalarFifoParam.size
              case n if isVector(n) => cuS.param.vectorFifoParam.size
            }
          case memP:pir.node.SRAM => cuS.param.sramParam.depth
          case memP:pir.node.ArgIn => cuS.param.scalarFifoParam.size
          case memP:pir.node.Reg => cuS.param.scalarFifoParam.size
        }
      case cuS:spade.node.ArgFringe =>
        1
    }

  }

  def linkTp(n:Link) = n match {
    case n if isBit(n) => "ctrl"
    case n if isWord(n) => "scal"
    case n if isVector(n) => "vec"
  }

  def linkSrc(n:Link) = {
    val mem = memsOf(n).head
    mem match {
      case mem:ArgIn => globalOf(n.collectInTillMem[ArgInDef]().head).get
        // case mem:StreamIn => no store on StreamIn
      case mem => n.collectInTillMem[ContextEnable]().head
    }
  }

  def linkDst(n:Link) = dbgblk(s"linkDst($n)") {
    val mem = memsOf(n).head
    mem match {
      case mem:StreamOut => ctxEnOf(globalOf(mem).get).get
      case mem:ArgOut => globalOf(mem).get
      case mem:TokenOut => globalOf(mem).get
      case WithReaders(readers) => readers.head.collectInTillMem[ContextEnable]().head
    }
  }

  def srcSuffix(cuP:GlobalContainer) = cuP match {
    case x:pir.node.ArgFringe => "_out"
    case _ => ""
  }

  def dstSuffix(cuP:GlobalContainer) = cuP match {
    case x:pir.node.ArgFringe => "_in"
    case _ => ""
  }

  def latencyOf(n:ContextEnable) = {
    globalOf(n).get match {
      case cuP:FringeContainer => 100
      case cuP =>
        val cuS = cumap.mappedValue(cuP)
        val stages = cuS.collectDown[Stage]()
        Math.max(stages.size, 1)
    }
  }

}
