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

  case object Tst extends PIRBackend {
    val row:Int=14
    val col:Int=14
    override def genDir(name:String):String = s"${IR.config.cwd}/gen/P14x14/$name/"
    override def logDir(name:String):String = s"${IR.config.cwd}/gen/P14x14/$name/log"
    override def repDir(name:String):String = s"${IR.config.cwd}/gen/P14x14/$name/report"
    def runPasses():Result = {
      genpir() >>
      pirpass("gentst", s"--mapping=true --codegen=true --net=p2p --row=$row --col=$col --tungsten --psim=false".split(" ").toList) >>
      scommand(s"maketst", "make -C tungsten/".split(" "), timeout=3000, parseMake, MakeError.apply) >>
      scommand(s"runtst", "./tungsten/tungsten".split(" "), timeout=1000, parseTst, RunError.apply)
    }
  }

  override def backends: Seq[Backend] = Tst +: super.backends
}
