package pirc

import scala.collection.mutable
import scala.reflect._

trait ArgParser {
  val optMap = mutable.Map[String, List[String] => Unit]()

  def parse[T:ClassTag](default:T, values:List[String]):T = {
    (default match {
      case default:Int => values.head.toInt
      case default:String => values.head
      case default:Boolean => values.head == "true"
    }).asInstanceOf[T]
  }

  def register[T:ClassTag](key:String, default:T=null)(update:T => Unit):T = {
    optMap += key -> { values => update(parse(default, values)) }
    default
  }

  def setOption(args:Array[String]):Unit = {
    var key::rest = args.toList
    if (!key.contains("--")) return
    key = key.replace("--", "")
    var values = rest.span(!_.contains("--"))._2.toList
    if (key.contains("=")) {
      val newKey::newValues = key.split("=").toList
      key = newKey
      values = newValues
    } else if (values.isEmpty) {
      values = List("true")
    }
    if (optMap.contains(key)) optMap(key)(values)
  }
}
