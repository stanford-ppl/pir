package prism
package util

import scala.collection.mutable
import prism.codegen.CSVPrinter

case class ArgOption[T:ClassTag](key:String, default:Option[T], info:String) {
  var value:Option[T]=None
  def getValue = value.orElse(default)
  def updateValue(value:T):Unit = this.value = Some(value)
  def updateValue(values:List[String]):Unit = updateValue(parse(values))
  def parse[T:ClassTag](values:List[String]):T = {
    (implicitly[ClassTag[T]] match {
      case ct if ct == classTag[Int] => values.head.toInt
      case ct if ct == classTag[Long] => values.head.toLong
      case ct if ct == classTag[String] => values.head
      case ct if ct == classTag[Boolean] => values.head == "true"
      case ct if ct == classTag[(String,Long)] => 
        values.head.stripPrefix("(").stripSuffix(")").span(c => c == ',').map2(_.toLong)
    }).asInstanceOf[T]
  }
}

trait ArgParser {
  val optionMap = mutable.ListMap[String, ArgOption[_]]()

  def register[T:ClassTag](key:String, default:Option[T]=None, info:String=""):Unit = {
    if (optionMap.contains(key)) throw new Exception(s"$key already registered")
    optionMap += key -> ArgOption(key, default, info)
  }
  def register[T:ClassTag](key:String, default:T, info:String):Unit = register(key, Some(default), info)

  def getOption[T](key:String) = optionMap(key).getValue.asInstanceOf[Option[T]]
  def option[T](key:String):T = getOption[T](key).get

  def getArgOption[T](key:String) = optionMap.get(key).asInstanceOf[Option[ArgOption[T]]]

  def setOptionRec(args:List[String]):Unit = {
    args match {
      case arg::rest if isOption(arg) =>
        val (key, values, remain) = getValues(arg, rest)
        optionMap.get(key).foreach { option =>
          option.updateValue(values)
        }
        setOptionRec(remain)
      case _::args => setOptionRec(args)
      case Nil => 
    }
  }

  def writeConfig(path:String) = {
    new CSVPrinter {
      withCSV(path, "config.csv", false) {
        optionMap.foreach { case (key, opt) =>
          val row = newRow
          row("key") = key
          row(s"default") = opt.default.getOrElse("None")
          row(s"value") = opt.value.getOrElse("None")
        }
      }
    }
  }

  def setOption(args:List[String]):Unit = {
    setOptionRec(args)
  }

  def updateOption[T](key:String)(update:Option[T] => T) = {
    val orig = optionMap.get(key).getOrElse {
      bug(s"$key is not registered for update.")
    }.asInstanceOf[ArgOption[T]]
    val updated = update(orig.getValue)
    orig.value = Some(updated)
  }

  def printUsage = {
    optionMap.foreach { case (key, ArgOption(_, default, msg)) =>
      info(s"--$key: $msg ${default.map { default => s"[default=$default]" }.getOrElse("")}")
    }
  }

  def isOption(key:String) = key.contains("--")

  def getValues(origKey:String, rest:List[String]):(String, List[String], List[String]) = {
    var (values, remains) = rest.view.zipWithIndex.find{ case (a, i) => isOption(a) }.fold((rest, List[String]())) { case (_, i) => rest.splitAt(i) } 
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
