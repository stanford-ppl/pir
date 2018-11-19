package spade
package param

import prism.graph._
import prism.graph.implicits._
import scala.collection.mutable

trait DefaultParamLoader extends Transformer {

  def loadParam:TopParam = transform(TopParam())

  def getOpt[T](name:String):Option[T]

  def getOptOrElse[T](name:String, default:T):T = getOpt[T](name).getOrElse(default)

  def optIs[T](name:String, value:T) = getOpt[T](name).fold(false) { _ == value }

  override def transform[T](n:T):T = dbgblk(s"transform($n)") { 
    n.to[Parameter].fold(super.transform(n)) { n =>
      n.mapFields[TopParam] {
        case ("wordWidth", x, arg) => getOptOrElse("word",arg)
        case ("vecWidth", x, arg) => getOptOrElse("vec", arg)
        case ("clockFrequency", x, arg) => getOptOrElse("clock", arg)
        case ("pattern", x, arg) if optIs("net","asic") => transform(AsicPattern())
        case ("pattern", x, arg) if optIs("pattern","checkerboard") => 
          val cb = Checkerboard()
          transform(cb)
        case (_, x, arg) => transform(arg)
      }.mapFields[Checkerboard] {
        case ("row", x, arg) => getOptOrElse("row", arg)
        case ("col", x, arg) => getOptOrElse("col", arg)
        case (_, x, arg) => transform(arg)
      }.mapFields[NetworkParam] {
        case ("linkProp", x, arg) => getOptOrElse("link-prop", arg)
        case ("flitWidth", x, arg) => getOptOrElse("flit-width", arg)
        case (_, x, arg) => transform(arg)
      }.mapFields[PCUParam] {
        case ("numStage", x, arg) => getOptOrElse("pcu-stage", arg)
        case (_, x, arg) => transform(arg)
      }.mapFields[PMUParam] {
        case ("numStage", x, arg) => getOptOrElse("pmu-stage", arg)
        case (_, x, arg) => transform(arg)
      }.mapFields[FIFOParam] {
        case ("count", x, arg) if x.granularity=="bit" => getOptOrElse(s"${cuTp(x)}-cfifo",arg)
        case ("count", x, arg) if x.granularity=="word" => getOptOrElse(s"${cuTp(x)}-sfifo",arg)
        case ("count", x, arg) if x.granularity=="vec" => getOptOrElse(s"${cuTp(x)}-vfifo",arg)
        case (_, x, arg) => transform(arg)
      }.asInstanceOf[T]
    }
  }
  def cuTp(n:FIFOParam):String = {
    val tp = n.cuParam match {
      case _:PCUParam => "pcu"
      case _:PMUParam => "pmu"
      case _:DramAGParam => "dag"
    }
    println(n, tp)
    tp
  }
    
}
