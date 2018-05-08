package prism.util

import prism._
import scala.collection.mutable

trait Memorization {
  type Cache[I,O] = prism.util.Cache[I,O]
  val Cache = prism.util.Cache

  var memorizing = false

  private val caches = mutable.ListBuffer[Cache[_,_]]()
  def addCache(cache:Cache[_,_]) = caches += cache
  def resetCache(input:Any) = caches.foreach(_.resetCache(input)) 
  def resetAllCaches = caches.foreach(_.resetAll)

  def memorize[I,O](lambda:I => O):Cache[I,O] = {
    val cache = Cache[I,O](this, lambda)
    caches += cache
    cache
  }
}
case class Cache[I,O](memorization:Memorization, lambda:I => O) {
  val memory = mutable.Map[Any,O]()
  def apply(input:I) = if (memorization.memorizing) memory.getOrElseUpdate(input, lambda(input)) 
                       else lambda(input)
  def resetCache(input:Any) = memory -= input
  def resetAll = memory.clear
}
