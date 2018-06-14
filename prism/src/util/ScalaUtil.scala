package prism
package util

import scala.collection.mutable

trait ScalaUtil {
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

  def assertUnify[A,B](list:Iterable[A],info:String)(lambda:A => B):B = {
    val res = list.map(lambda)
    assert(res.toSet.size==1, s"$list doesnt have the same $info = $res")
    res.head
  }

  def assertOneOrLess[A](list:Iterable[A], info:String):Option[A] = {
    assert(list.size<=1, s"More than one element in $list for $info")
    list.headOption
  }

  def zipMap[A,B,T](a:Option[A], b:Option[B])(lambda:(A,B) => T):Option[T] = {
    (a,b).zipped.map { case (a,b) => lambda(a,b) }.headOption
  }

  def zipMap[A,B,C,T](a:Option[A], b:Option[B], c:Option[C])(lambda:(A,B,C) => T):Option[T] = {
    (a,b,c).zipped.map { case (a,b,c) => lambda(a,b,c) }.headOption
  }

}
