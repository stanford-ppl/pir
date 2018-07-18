package prism
package util

import scala.collection.mutable

trait Memorization {
  type Cache[I,O] = prism.util.Cache[I,O]
  val Cache = prism.util.Cache

  var memorizing = false

  private val caches = mutable.Map[String,Cache[_,_]]()
  def resetCache(input:Any) = caches.values.foreach(_.resetCache(input)) 
  def resetAllCaches = caches.values.foreach(_.resetAll)
  def memorize[I,O](key:String, input:I)(lambda:I => O):O = {
    caches.getOrElseUpdate(key, 
      Cache[I,O](this, lambda)
    ).asInstanceOf[Cache[I,O]].apply(input)
  }

  def withMemory[T](block: => T):T = {
    val saved = memorizing
    memorizing = true
    val res = block
    memorizing = saved
    resetAllCaches
    res
  }

}
case class Cache[I,O](memorization:Memorization, lambda:I => O) {
  val memory = mutable.Map[Any,O]()
  def apply(input:I) = if (memorization.memorizing) memory.getOrElseUpdate(input, lambda(input)) 
                       else lambda(input)
  def resetCache(input:Any) = memory -= input
  def resetAll = memory.clear
}
