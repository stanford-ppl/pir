package prism
package util

import scala.collection.mutable

trait ArgParser {
  val optMap = mutable.ListMap[String, (List[String] => Unit, Any, String)]()

  def parse[T:ClassTag](default:T, values:List[String]):T = {
    (default match {
      case default:Int => values.head.toInt
      case default:String => values.head
      case default:Boolean => values.head == "true"
    }).asInstanceOf[T]
  }

  def register[T:ClassTag](key:String, default:T=null, info:String="")(update:T => Unit):T = {
    optMap += key -> ({ values => update(parse(default, values)) }, default, info)
    default
  }

  def isOption(key:String) = key.contains("--")

  def getValues(origKey:String, rest:List[String]):(String, List[String], List[String]) = {
    var (values, remains) = rest.zipWithIndex.find{ case (a, i) => isOption(a) }.fold((rest, List[String]())) { case (_, i) => rest.splitAt(i) } 
    var key = origKey.replace("--", "")
    if (key.contains("=")) {
      val newKey::newValues = key.split("=").toList
      key = newKey
      values = newValues
    } else if (values.isEmpty) {
      values = List("true")
    }
    (key, values, remains)
  }

  val loadedOptions = mutable.Map[String, List[String]]()
  def setOption(args:List[String]):Unit = {
    args match {
      case arg::rest if isOption(arg) =>
        val (key, values, remain) = getValues(arg, rest)
        if (optMap.contains(key)) {
          loadedOptions += key -> values
        }
        setOption(remain)
      case _::args => setOption(args)
      case Nil => 
        // Update twice to avoid any order with option
        (0 until 2).foreach { _ => 
          loadedOptions.foreach { case (key, values) =>
            val (update, _, _) = optMap(key)
            update(values)
          }
        }
        loadedOptions.clear
    }
  }

  def printUsage = {
    optMap.foreach { case (key, (update, default, msg)) =>
      info(s"--$key $msg [default=$default]")
    }
  }
}
