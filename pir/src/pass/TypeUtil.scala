package pir
package pass

import pir.node._
import prism.graph._
import prism.util._
import scala.collection.mutable

trait TypeUtil { self:PIRPass =>

  def noPlaceAndRoute = spadeParam.isAsic || spadeParam.isP2P || spadeParam.isInf

  implicit class CtxUtil(ctx:Context) {
    def reads:Seq[LocalOutAccess] = ctx.collectDown[LocalOutAccess]().filterNot { _.isLocal }
    def writes:Seq[LocalInAccess] = ctx.collectDown[LocalInAccess]().filterNot { _.isLocal }
    def ctrs:Seq[Counter] = ctx.collectDown[Counter]()
    def ctrlers = ctx.collectDown[Controller]()
    def ctrler(ctrl:ControlTree) = {
      assertOneOrLess(
        ctx.ctrlers.filter { _.ctrl.get == ctrl }, 
        s"$ctx.ctrler with ($ctrl)"
      )
    }
    def getCtrler(ctrl:ControlTree) = {
      assertOne(
        ctx.ctrlers.filter { _.ctrl.get == ctrl }, 
        s"$ctx.ctrler with ($ctrl)"
      )
    }
    def activeRate(n:Float) = ctx.getMeta[Float]("activeRate").update(n)
    def activeRate = ctx.getMeta[Float]("activeRate").v
    def stallRate(n:Float) = ctx.getMeta[Float]("stallRate").update(n)
    def stallRate = ctx.getMeta[Float]("stallRate").v
    def starveRate(n:Float) = ctx.getMeta[Float]("starveRate").update(n)
    def starveRate = ctx.getMeta[Float]("starveRate").v
  }

  implicit class IRRuntimeOp(n:PIRNode) {
    def getBound:Option[Int] = n.getMeta[Option[Int]]("bound").getOrElseUpdate(boundProp(n).as[Option[Int]])

    def psimState(s:String) = n.getMeta[Float]("psimState").update(s)
    def psimState = n.getMeta[String]("psimState").v
  }

  implicit class NodeRuntimeOp[N<:IR](n:N) {
    def vecMeta:MetadataLike[Int] = n.getMeta[Int]("vec")
    def presetVecMeta:MetadataLike[Int] = n.getMeta[Int]("presetVec")
    def tpMeta:MetadataLike[BitType] = n.getMeta[BitType]("tp")
    def setVec(v:Int) = n.getMeta[Int]("vec").apply(v)
    def setTp(v:BitType) = n.getMeta[BitType]("tp").apply(v)
    def setTp(v:Option[BitType]) = n.getMeta[BitType]("tp").update(v)
    def getVec:Int = /*withLogger(self) {*/ n.inferVec.orElse(n.singleOutput.flatMap { _.inferVec }) //}
      .getOrElse(bug(s"Don't know how to infer vec of ${dquote(n)}"))
    def getTp:BitType = /*withLogger(self) { */n.inferTp.orElse(n.singleOutput.flatMap { _.inferTp }) //}
      .getOrElse(bug(s"Don't know how to infer type of ${dquote(n)}"))
    def singleOutput = n match {
      case n:Node[_] =>
        if (n.localOuts.size == 1) Some(n.localOuts.head) else None
      case _ => None
    }
  }

  val UnderControlBlock = MatchRule[PIRNode, ControlBlock] { case n =>
    n.ancestors.collectFirst { case n:ControlBlock => n }
  }

  def isRateMatchingFIFO(n:BufferRead) = (n.out.getVec != n.in.getVec) | (n.en.isConnected & n.en.getVec > 1)
  //def isRateMatchingFIFO(n:BufferRead) = n.out.getVec != n.in.getVec | n.en.getVec > 1

  def boundProp(n:PIRNode):Option[Any] = dbgblk(s"boundProp($n)"){
    n match {
      case Const(v) => Some(v)
      case n:BufferRead => n.in.T.getBound
      case n:BufferWrite => n.data.T.getBound
      case n:GlobalInput => n.in.T.getBound
      case n:GlobalOutput => n.in.T.getBound
      case n => None
    }
  }

  def quoteSrcCtx(n:Any, delim:String="\n") = n match {
    case n:PIRNode =>
      var msg = dquote(n)
      n.name.v.foreach { n => msg += s": $n" }
      msg += " " + n.srcCtx.get.mkString(delim)
      n.ctx.map { ctx => msg += s" ($ctx)"}
      msg
    case n:Barrier =>
      var msg = dquote(n)
      n.name.v.foreach { n => msg += s": $n" }
      msg += " " + n.srcCtx.get.mkString(delim)
      msg
    case n:ControlTree =>
      s"$n[${n.uid.get.mkString(",")}] ${n.srcCtx.get.mkString(delim)}"
    case n => s"$n"
  }

  implicit class EdgeOp(x:IR) {
    def matchWith(y:IR):Boolean = {
      (x,y) match {
        case (x:Input[_], y:Input[_]) if x.connected.size != y.connected.size => false
        case (x:Input[_], y:Input[_]) => x.connected.zip(y.connected).forall { case (x,y) => x.matchWith(y) }
        case (x:Output[_],y:Output[_]) if x == y => true
        case (WithNode(Const(x)), WithNode(Const(y))) if x == y => true
        case (x,y) => false
      }
    }
  }

  def topName = if (config.asModule) config.moduleName.getOrElse(pirTop.name.get + "Top") else "Top"

  def isVecLink(n:IR) = {
    if (n.getVec == 1) false
    else if (n.getTp == Bool && n.getVec < 32) false
    else true
  }

  def isCtrlLink(n:GlobalOutput) = {
    n.getVec == 1 && n.getTp == Bool
  }


  def analyzeBackEdge:Set[(Output[PIRNode], Input[PIRNode])] = dbgblk(s"analyzeBackEdge"){
    type Node = PIRNode
    type Edge = (Output[Node], Input[Node])
    def dfs(node:Node, backEdges:mutable.Set[Edge], visitedStack:List[Node]):Unit = {
      node.outs.foreach { out =>
        out.connected.foreach { in =>
          if (!backEdges.contains((out,in))) {
            val dst = in.src.global.get
            if (visitedStack.contains(dst)) {
              dbg(s"backEdges: ${out.src.id} -> ${in.src.id} ${out.src.global.get.id} -> ${in.src.global.get.id}")
              dbg(s"stack=${visitedStack}")
              backEdges += (out -> in)
            }
            else
              dfs(dst, backEdges, visitedStack :+ node)
          }
        }
      }
    }
    val nodes = pirTop.collectChildren[GlobalContainer]
    val starts = nodes.filter { 
      case g:ArgFringe => true
      case g => g.ins.forall { !_.isConnected }
    }
    dbg(s"starts=$starts")

    // Performing DFS
    val backEdges = mutable.Set.empty[Edge]
    starts.foreach { node =>
      dfs(node, backEdges, List.empty)
    }

    backEdges.toSet
  }

  def getAvailableCUs:List[spade.node.Terminal] = {
    import spade.param._
    var cus = spadeTop.cus
    if (spadeParam.isAsic || spadeParam.isInf)
      return cus
    var reservePCUs = config.option[Int]("reserve-pcu")
    var reservePMUs = config.option[Int]("reserve-pmu")
    var reserveDAGs = config.option[Int]("reserve-dag")
    var reserveMCs = config.option[Int]("reserve-mc")
    cus = cus.filterNot { cu =>
      cu.params match {
        case Some(param:PCUParam) if reservePCUs > 0 => reservePCUs-=1; true
        case Some(param:PMUParam) if reservePMUs > 0 => reservePMUs-=1; true
        case Some(param:DramAGParam) if reserveDAGs > 0 => reserveDAGs-=1; true
        case Some(param:MCParam) if reserveMCs > 0 => reserveMCs-=1; true
        case param => false
      }
    }
    cus
  }

}

