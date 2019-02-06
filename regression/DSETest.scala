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

trait DSETest extends PlasticineTest { test =>
  //def param:Param[_]
  
  override def runtimeArgs = Args(Seq(""))

  case object P2PTst extends PIRBackend {
    val row:Int=14
    val col:Int=14
    override def shouldRun = {
      test.name.contains("SimpleDotProduct") |
      test.name.contains("SumSquare")
    }
    def runPasses():Result = {
      genpir() >>
      runpir() >>
      mappir(s"--net=p2p --row=$row --col=$col") >>
      runtst()
    }
  }

  override def backends: Seq[Backend] = P2PTst +: super.backends
}
