package pir.graph.traversal
import pir.graph._
import pir._
import pir.PIRMisc._
import pir.graph.mapping._
import scala.util.{Try, Success, Failure}

class PIRMapping(implicit val design: Design) extends Traversal{
  import PIRMapping._

  var mapping:CUMapping = _

  override def reset = {
    mapping = null
  }

  override def traverse = {
    Try {
      mapping = new CUMapping() 
    } match {
      case Success(_) =>
        info(s"Mapping succeeded") 
      case Failure(e) =>
        info(s"Mapping failed")
    }
  } 

  override def finPass = {
    info("Finishing PIR Mapping")
    info(s"Mapping printed in ${PIRMapping.getPath}")
    mapping.printMap
    emitln(s"MappingExceptions:")
    design.mapExceps.foreach { h =>
      emitln(h)
    }
    close
  }
}
object PIRMapping extends Printer {
  override val stream = newStream("Mapping.txt") 
}
