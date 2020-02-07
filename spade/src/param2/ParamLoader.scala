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
    (n match {
      case n:TopParam =>
        n.mapFieldWithName {
          case ("wordWidth", x, arg) => getOptOrElse("word",arg)
          case ("vecWidth", x, arg) => getOptOrElse("vec", arg)
          case ("scheduled", x, arg) => getOptOrElse("scheduled", arg)
          case ("clockFrequency", x, arg) => getOptOrElse("clock", arg)
          case ("pattern", x, arg) if optIs("net","asic") => transform(AsicPattern())
          case ("pattern", x, arg) if optIs("net","inf") => transform(InfinatePattern())
          case ("pattern", x, arg) if optIs("pattern","checkerboard") => transform(Checkerboard())
          case ("pattern", x, arg) if optIs("pattern","mcmcstrip") => transform(MCMColumnStrip())
          case ("memTech", x, arg) => getOptOrElse("mem-tech", arg)
          case (_, x, arg) => transform(arg)
        }
      case n:Checkerboard =>
        n.mapFieldWithName {
          case ("row", x, arg) => getOptOrElse("row", arg)
          case ("col", x, arg) => getOptOrElse("col", arg)
          case (_, x, arg) => transform(arg)
        }
      case n:NetworkParam =>
        n.mapFieldWithName {
          case ("topology", x, arg) if optIs("net","p2p") => "p2p"
          case ("linkProp", x, arg) => getOptOrElse("link-prop", arg)
          case ("flitWidth", x, arg) => getOptOrElse("flit-width", arg)
          case ("numVC", x, arg) if x.granularity == "vec" => getOptOrElse("vc", arg)
          case (_, x, arg) => transform(arg)
        }
      case n:PCUParam =>
        n.mapFieldWithName {
          case ("numStage", x, arg) => getOptOrElse("pcu-stage", arg)
          case ("numVin", x, arg) => getOptOrElse("pcu-vin", arg)
          case ("numVout", x, arg) => getOptOrElse("pcu-vout", arg)
          case ("numSin", x, arg) => getOptOrElse("pcu-sin", arg)
          case ("numSout", x, arg) => getOptOrElse("pcu-sout", arg)
          case (_, x, arg) => transform(arg)
        }
      case n:PMUParam =>
        n.mapFieldWithName {
          case ("numStage", x, arg) => getOptOrElse("pmu-stage", arg)
          case ("numVin", x, arg) => getOptOrElse("pmu-vin", arg)
          case ("numVout", x, arg) => getOptOrElse("pmu-vout", arg)
          case ("numSin", x, arg) => getOptOrElse("pmu-sin", arg)
          case ("numSout", x, arg) => getOptOrElse("pmu-sout", arg)
          case (_, x, arg) => transform(arg)
        }
      case n:DramAGParam =>
        n.mapFieldWithName {
          case ("numStage", x, arg) => getOptOrElse("dag-stage", arg)
          case (_, x, arg) => transform(arg)
        }
      case n:FIFOParam =>
        n.mapFieldWithName {
          case ("count", x, arg) if x.granularity=="bit" => getOptOrElse(s"${cuTp(x)}-cfifo",arg)
          case ("count", x, arg) if x.granularity=="word" => getOptOrElse(s"${cuTp(x)}-sfifo",arg)
          case ("count", x, arg) if x.granularity=="vec" => getOptOrElse(s"${cuTp(x)}-vfifo",arg)
          case ("depth", x, arg) => getOptOrElse(s"fifo-depth",arg)
          case (_, x, arg) => transform(arg)
        }
      case n => super.transform(n)
    }).asInstanceOf[T]
  }
  def cuTp(n:FIFOParam):String = {
    n.cuParam match {
      case _:PCUParam => "pcu"
      case _:PMUParam => "pmu"
      case _:DramAGParam => "dag"
    }
  }
    
}
