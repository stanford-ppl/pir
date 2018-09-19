package prism
package util

import scala.collection.mutable

trait Memorization extends Logging {
  type Cache[I,O] = prism.util.Cache[I,O]
  val Cache = prism.util.Cache

  var memorizing = false

  val caches = mutable.Map[String,Cache[_,_]]()
  def resetCache(input:Any) = caches.values.foreach(_.resetCache(input)) 
  def resetCacheOn(reset:Any => Boolean) = caches.values.foreach(_.resetCacheOn(reset))
  def resetAllCaches = caches.values.foreach(_.resetAll)
  def memorize[I,O](key:String, input:I)(lambda:I => O):O = {
    caches.getOrElseUpdate(key, 
      Cache[I,O](this, key, lambda)
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

  def withOutMemory[T](block: => T):T = {
    val saved = memorizing
    memorizing = false
    val res = block
    memorizing = saved
    resetAllCaches
    res
  }

}
case class Cache[I,O](memorization:Memorization, name:String, lambda:I => O) {
  override def toString = s"Cache($name)"
  val memory = mutable.Map[Any,O]()
  def apply(input:I) = {
    if (memorization.memorizing) memory.getOrElseUpdate(input, lambda(input)) 
                       else lambda(input)
  }
  def resetCache(input:Any) = memory -= input
  def resetCacheOn(reset:Any => Boolean) = {
    val keys = memory.keys.filter(reset)
    keys.foreach(resetCache)
    if (keys.nonEmpty) memorization.dbg(s"$this.resetCacheOn($keys)")
  }
  def resetAll = memory.clear
}
