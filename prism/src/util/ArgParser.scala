package prism
package util

import scala.collection.mutable

case class ArgOption[T:ClassTag](key:String, default:Option[T], info:String) {
  var value:Option[T]=None
  def getValue = value.orElse(default)
  def updateValue(values:List[String]) = value = Some(parse(values))
  def parse[T:ClassTag](values:List[String]):T = {
    (implicitly[ClassTag[T]] match {
      case ct if ct == classTag[Int] => values.head.toInt
      case ct if ct == classTag[Long] => values.head.toLong
      case ct if ct == classTag[String] => values.head
      case ct if ct == classTag[Boolean] => values.head == "true"
    }).asInstanceOf[T]
  }
}

trait ArgParser {
  val optionMap = mutable.ListMap[String, ArgOption[_]]()

  def register[T:ClassTag](key:String, default:Option[T], info:String):Unit = {
    optionMap += key -> ArgOption(key, default, info)
  }
  def register[T:ClassTag](key:String, default:T, info:String):Unit = register(key, Some(default), info)
  def register[T:ClassTag](key:String, info:String):Unit = register(key, None, info)
  def register[T:ClassTag](key:String):Unit = register(key, None, "")

  def getOption[T](key:String) = optionMap(key).getValue.asInstanceOf[Option[T]]
  def option[T](key:String):T = getOption[T](key).get

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

  def printUsage = {
    optionMap.foreach { case (key, ArgOption(_, default, msg)) =>
      info(s"--$key: $msg ${default.map { default => s"[default=$default]" }.getOrElse("")}")
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
