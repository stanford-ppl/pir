package prism.util

import scala.reflect._
import scala.collection.mutable

trait Memorization {
  //val memorizing = true
  val memorizing = false
  private val caches = mutable.ListBuffer[Cache[_,_]]()
  case class Cache[I:ClassTag,O](lambda:I => O) {
    caches += this
    val memory = mutable.Map[I,O]()
    def memorize(input:I) = if (memorizing) memory.getOrElseUpdate(input, lambda(input)) else lambda(input)
    def resetCache(input:Any) = input match { case input:I => memory -= input; case _ => }
    def resetAll = memory.clear
  }
  def resetCache(input:Any) = caches.foreach(_.resetCache(input)) 
  def resetAllCaches = caches.foreach(_.resetAll)
}
