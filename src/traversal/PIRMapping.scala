package pir.graph.traversal
import pir.graph._
import pir._
import pir.PIRMisc._
import pir.graph.mapper._
import scala.util.{Try, Success, Failure}

class PIRMapping(implicit val design: Design) extends Traversal{

  var mapping:CUMapper.M = _

  override def reset = {
    mapping = null
  }

  override def traverse = {
    Try(mapping = CUMapper.map) match {
      case Success(_) =>
        info(s"Mapping succeeded") 
      case Failure(e) => e match {
        case me:MappingException =>
          info(s"Mapping failed")
          val p:Printer = new Printer { override val stream = newStream(Config.mapFile) }
          p.emitln("Mapping Exceptions:")
          p.emitln(me)
          p.flush
        case _ => throw e
      }
    }
  } 

  override def finPass = {
    if (Config.debug) {
      //val p:Printer = new Printer { override val stream = newStream(Config.mapFile) }
      //val p:Printer = new Printer { val a = 3 }
      //if (mapping!=null) CUMapper.printMap(mapping)(p)
      //p.emitln(s"MappingExceptions:")
      //design.mapExceps.foreach { h =>
      //  p.emitln(h)
      //}
      //p.close
      //info(s"Mapping printed in ${p.getPath}")
    }
    info("Finishing PIR Mapping")
  }
}
