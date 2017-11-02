package pir.pass

import pir._

import pirc._
import pirc.util._

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

class ForwardRef(implicit design: PIR) extends Pass with Logger {
  import pirmeta._

  def shouldRun = true 

  override lazy val stream = newStream(s"ForwardRef.log")

  addPass {
    dprintln(s"names:")
    nameOf.map.foreach { case (name, node) =>
      dprintln(s"  $name - $node")
    }
    design.toUpdate.foreach { f =>
      try {
        f()
      } catch {
        case e:java.util.NoSuchElementException => 
          dprintln(s"$e")
          warn(e)
        case e:Throwable => throw e
      }
    }
    design.toUpdate.clear
  } 

}
object ForwardRef {
  // Collect outer controllers that are in the same CU
  
  //def collectMCParent(implicit design:PIR) = {
    //design.top.innerCUs.foreach { inner =>
      ////TODO: hack add dep and parent of MemoryController here
      //inner match {
        //case mc:MemoryController =>
          //val addrPipe = mc.mctpe match {
            //case (TileLoad | TileStore) => mc.ofs.writer.ctrler.asInstanceOf[InnerController]
            //case (Gather | Scatter)=> mc.addrs.writer.ctrler.asInstanceOf[InnerController]
          //}
          ////mc.addDep(addrPipe)
          //mc.parent(addrPipe.parent)
          //mc.parent.addChildren(mc)
          //mc.mctpe match {
            //case TileLoad => //mc.vdata.readers.foreach { _.ctrler.asInstanceOf[ComputeUnit].addDep(mc) }
              //val lenPipe = mc.len.writer.ctrler.asInstanceOf[InnerController]
              ////if (mc.parent==lenPipe.parent) mc.addDep(lenPipe)
            //case TileStore =>
              ////mc.addDep(mc.vdata.writer.ctrler.asInstanceOf[ComputeUnit])
              //val lenPipe = mc.len.writer.ctrler.asInstanceOf[InnerController]
              ////if (mc.parent==lenPipe.parent) mc.addDep(lenPipe)
            //case Gather =>
            //case Scatter =>
              ////mc.addDep(mc.vdata.writer.ctrler.asInstanceOf[ComputeUnit])
          //}
        //case icu:InnerController =>
      //}
    //}
  //}

  //def collectOuters(implicit design:PIR) = {
    //design.top.innerCUs.foreach { inner =>
      //val outers = ListBuffer[OuterController]()
      //var child:ComputeUnit = inner
      //// Make a copy of ancestors' CounterChains 
      //while (child.isLast && !child.parent.isInstanceOf[Top]) {
        //val parent = child.parent.asInstanceOf[OuterController]
        //outers += parent
        //parent.inner = inner
        //parent.cchains.foreach { cc => inner.getCopy(cc.original) }
        //child = child.parent.asInstanceOf[ComputeUnit]
      //}
      //inner.outers = outers.toList
    //}
  //}

}
