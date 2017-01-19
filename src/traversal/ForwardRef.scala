package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.enums._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

class ForwardRef(implicit val design: Design) extends Traversal{

  private val nameMap = Map[String, Node]()

  override def reset = nameMap.clear()
  override def traverse:Unit = {
    design.allNodes.foreach(n => addName(n))
    design.toUpdate.foreach { case (k,f) =>
      val n:Node = getByName(k)
      f(n)
    }
    design.toUpdate.clear
    ForwardRef.collectOuters
  } 

  def addName(n:Node):Unit = n.name.foreach { name => 
    n match {
      case c:Controller => 
        assert(!nameMap.contains(name), s"Already create controller with name ${name}: ${n}")
        nameMap += (name -> c)
      case c:OffChip => 
        assert(!nameMap.contains(name), s"Already create name ${name}: ${n}")
        nameMap += (name -> c)
      case p:Primitive =>
        val s = ForwardRef.getPrimName(p.ctrler, name)
        assert(!nameMap.contains(s),
          s"Already create primitive with name ${s} for controller ${p.ctrler}")
        nameMap += (s -> p)
      case w:Scalar =>
      case w:Vector =>
      case w:Port =>
        //assert(false, "No support for adding name for wire yet!")
    }
  }

  def getByName(s:String):Node = {
    assert(nameMap.contains(s), s"No node defined with name:${s} \nnameMap:${nameMap}")
    nameMap(s)
  }

  override def finPass = {
    info("Finishing updating forward referenced nodes")
  }

}
object ForwardRef {
  def getPrimName(ctrler:Controller, name:String) = s"${ctrler.name.fold("")(cn => s"${cn}_")}${name}"
  def getPrimName(ctrler:String, name:String) = s"${ctrler}_${name}"
  // Collect outer controllers that are in the same CU
  def collectOuters(implicit design:Design) = {
    design.top.innerCUs.foreach { inner =>
      //TODO: hack add dep and parent of MemoryController here
      inner match {
        case mc:MemoryController =>
          val addrPipe = mc.mctpe match {
            case (TileLoad | TileStore) => mc.ofs.writer.ctrler.asInstanceOf[InnerController]
            case (Gather | Scatter)=> mc.addrs.writer.ctrler.asInstanceOf[InnerController]
          }
          mc.addDep(addrPipe)
          mc.parent(addrPipe.parent)
          mc.parent.addChildren(mc)
          mc.mctpe match {
            case TileLoad => //mc.vdata.readers.foreach { _.ctrler.asInstanceOf[ComputeUnit].addDep(mc) }
              val lenPipe = mc.len.writer.ctrler.asInstanceOf[InnerController]
              if (mc.parent==lenPipe.parent) mc.addDep(lenPipe)
            case TileStore =>
              mc.addDep(mc.vdata.writer.ctrler.asInstanceOf[ComputeUnit])
              val lenPipe = mc.len.writer.ctrler.asInstanceOf[InnerController]
              if (mc.parent==lenPipe.parent) mc.addDep(lenPipe)
            case Gather =>
            case Scatter =>
              mc.addDep(mc.vdata.writer.ctrler.asInstanceOf[ComputeUnit])
          }
        case icu:InnerController =>
      }
      val outers = ListBuffer[OuterController]()
      var child:ComputeUnit = inner
      // Make a copy of ancestors' CounterChains 
      while (child.isLast && !child.parent.isInstanceOf[Top]) {
        val parent = child.parent.asInstanceOf[OuterController]
        outers += parent
        parent.inner = inner
        //parent.cchains.foreach { cc => inner.getCopy(cc.original) }
        child = child.parent.asInstanceOf[ComputeUnit]
      }
      inner.outers = outers.toList
    }
  }

}
