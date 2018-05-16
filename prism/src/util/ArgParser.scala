package prism
package util

import scala.collection.mutable

case class ArgOption[T](key:String, default:T, info:String) {
  var value:Option[T]=None
  def getValue = value.getOrElse(default)
  def updateValue(values:List[String]) = value = Some(parse(values))
  def parse[T:ClassTag](values:List[String]):T = {
    (default match {
      case default:Int => values.head.toInt
      case default:String => values.head
      case default:Boolean => values.head == "true"
    }).asInstanceOf[T]
  }
}

trait ArgParser {
  // Map[Key, (value, default, info)]
  val optionMap = mutable.ListMap[String, ArgOption[_]]()

  def register[T](key:String, default:T, info:String=""):Unit = {
    optionMap += key -> ArgOption(key, default, info)
  }

  def option[T](key:String) = optionMap(key).getValue.asInstanceOf[T]

  def printUsage = {
    optionMap.foreach { case (key, ArgOption(_, default, msg)) =>
      info(s"--$key $msg [default=$default]")
    }
  }

  def setOption(args:List[String]):Unit = {
    args match {
      case arg::rest if isOption(arg) =>
        val (key, values, remain) = getValues(arg, rest)
        optionMap.get(key).foreach { option => option.updateValue(values) }
        setOption(remain)
      case _::args => setOption(args)
      case Nil => 
    }
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
}
