package prism.util

import scala.collection.mutable

trait ScalaAlias {
  type ClassTag[T] = scala.reflect.ClassTag[T]
  def classTag[T](implicit ctag: ClassTag[T]) = scala.reflect.classTag[T]
  type TypeTag[T] = scala.reflect.runtime.universe.TypeTag[T]
  def typeTag[T](implicit ctag: TypeTag[T]) = scala.reflect.runtime.universe.typeTag[T]
  def typeOf[T](implicit ctag: TypeTag[T]) = scala.reflect.runtime.universe.typeOf[T]

  implicit val postfixOps = scala.language.postfixOps
  implicit val implicitConversions = scala.language.implicitConversions
  implicit val hgherKinds = scala.language.higherKinds
  implicit val reflectiveCalls = scala.language.reflectiveCalls
  implicit val existentials = scala.language.existentials

  type ListBuffer[T] = scala.collection.mutable.ListBuffer[T]
  val ListBuffer = scala.collection.mutable.ListBuffer

  val Try = scala.util.Try
  val Success = scala.util.Success
  val Failure = scala.util.Failure

  def getOrElseUpdate[K,V](map:mutable.Map[K,V], k:K)(vFunc: => V) = {
    if (map.contains(k)) map(k) else {
      val v = vFunc
      map += k -> v
      v
    }
  }
}
