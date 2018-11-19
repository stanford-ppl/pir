package spade
package param

import prism._
import prism.graph._
import prism.graph.implicits._
import scala.collection.mutable

trait DefaultParamLoader extends Transformer {

  def loadParam:TopParam = transform(TopParam())

  def getOpt[T](name:String):Option[T]

  def getOptOrElse[T](name:String, default:T):T = getOpt[T](name).getOrElse(default)

  def optIs[T](name:String, value:T) = getOpt[T](name).fold(false) { _ == value }

  override def transform[T](n:T):T = dbgblk(s"transform($n)") { (n match {
    case n:TopParam =>
      n.mapFields[TopParam] {
        case ("wordWidth", arg) => getOptOrElse("word",arg)
        case ("vecWidth", arg) => getOptOrElse("vec", arg)
        case ("clockFrequency", arg) => getOptOrElse("clock", arg)
        case ("pattern", arg) if optIs("net","asic") => transform(AsicPattern())
        case ("pattern", arg) if optIs("pattern","checkerboard") => 
          val cb = Checkerboard()
          transform(cb)
        case (_, arg) => transform(arg)
      }

    case n:Checkerboard =>
      n.mapFields[Checkerboard] {
        case ("row", arg) => getOptOrElse("row", arg)
        case ("col", arg) => getOptOrElse("col", arg)
        case (_, arg) => transform(arg)
      }

    case n:NetworkParam =>
      n.mapFields[NetworkParam] {
        case ("linkProp", arg) => getOptOrElse("link-prop", arg)
        case ("flitWidth", arg) => getOptOrElse("flit-width", arg)
        case (_, arg) => transform(arg)
      }

    case n:PCUParam =>
      n.mapFields[PCUParam] {
        case ("numStage", arg) => getOptOrElse("pcu-stage", arg)
        case (_, arg) => transform(arg)
      }

    case n:PMUParam =>
      n.mapFields[PMUParam] {
        case ("numStage", arg) => getOptOrElse("pmu-stage", arg)
        case (_, arg) => transform(arg)
      }

    case n:FIFOParam =>
      val tp = n.collectOut[CUParam]().head match {
        case _:PCUParam => "pcu"
        case _:PMUParam => "pmu"
        case _:DramAGParam => "dag"
      }
      n.mapFields[FIFOParam] {
        case ("count", arg) if n.granularity=="bit" => getOptOrElse(s"$tp-cfifo",arg)
        case ("count", arg) if n.granularity=="word" => getOptOrElse(s"$tp-sfifo",arg)
        case ("count", arg) if n.granularity=="vec" => getOptOrElse(s"$tp-vfifo",arg)
        case (_,arg) => transform(arg)
      }
    case n => super.transform[T](n)
  }).asInstanceOf[T] }

}
