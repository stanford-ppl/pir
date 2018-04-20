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

  def memorize[I:ClassTag,O](lambda:I => O):Cache[I,O] = {
    val cache = Cache[I,O](memorizing, lambda)
    caches += cache
    cache
  }
}
case class Cache[I:ClassTag,O](memorizing:Boolean, lambda:I => O) {
  val memory = mutable.Map[I,O]()
  def apply(input:I) = if (memorizing) memory.getOrElseUpdate(input, lambda(input)) else lambda(input)
  def resetCache(input:Any) = input match { case input:I => memory -= input; case _ => }
  def resetAll = memory.clear
}
