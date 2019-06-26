import spatial._
import argon.DSLTest
import spatial.util.spatialConfig
import utils.io.files._
import scala.reflect.runtime.universe._

abstract class Param[T:TypeTag] extends Product {
  def prefix = this.getClass.getSimpleName.replace("Param","")
  def fields = typeOf[T].members.sorted.collect {
    case m: MethodSymbol if m.isCaseAccessor => m.name.toString
  }.toList
  override def toString = {
    val fs = fields.zip(productIterator.toList).map { case (f,v) => s"${f}_$v" }.mkString("_")
    s"${prefix}_$fs"
  }
}

trait DSETest extends SpatialTest { test =>
  case object PIRCompile extends PIRBackend {
    val row:Int=14
    val col:Int=14
    def runPasses():Result = {
      genpir() >>
      pirpass("gentst", s"--mapping=true --codegen=true --net=inf --tungsten=false --dot=true --psim=false".split(" ").toList)
    }
  }

  override def backends: Seq[Backend] = 
    PIRCompile +:
    super.backends
}
