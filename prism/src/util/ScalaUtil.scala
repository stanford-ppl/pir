package prism
package util

import scala.collection.mutable

trait ScalaUtil extends ScalaAlias with ScalaUtilFunc

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

  type Try[U] = scala.util.Try[U]
  val Try = scala.util.Try
  val Success = scala.util.Success
  val Failure = scala.util.Failure
}

trait ScalaUtilFunc {
  def getOrElseUpdate[K,V](map:mutable.Map[K,V], k:K)(vFunc: => V) = {
    if (map.contains(k)) map(k) else {
      val v = vFunc
      map += k -> v
      v
    }
  }

  def assertUnify[A,B](list:Iterable[A],info:String)(lambda:A => B):Option[B] = {
    val res = list.map(lambda)
    assert(res.toSet.size<=1, s"$list doesnt have the same $info = $res")
    res.headOption
  }

  def assertOptionUnify[A,B](list:Iterable[A],info:String)(lambda:A => Option[B]):Option[B] = {
    val res = list.flatMap(a => lambda(a))
    assert(res.toSet.size<=1, s"$list doesnt have the same $info = $res")
    res.headOption
  }

  def assertIdentical[A](list:Iterable[A],info:String):Option[A] = {
    val res = list
    assert(res.toSet.size<=1, s"$list doesnt have the same $info = $res")
    res.headOption
  }

  def assertOneOrLess[A](list:Iterable[A], info:String):Option[A] = {
    assert(list.size<=1, s"More than one element in $list for $info")
    list.headOption
  }

  def assertOne[A](list:Iterable[A], info:String):A = {
    assert(list.size==1, s"Not exactly one element in $list for $info")
    list.head
  }

  def assertUnique[A](list:Iterable[A],info:String):Unit = {
    assert(list.toSet.size == list.size, s"$info is not unique=$list")
  }

  def zipOption[A,B,T](a:Option[A], b:Option[B]):Option[(A,B)] = {
    (a,b).zipped.headOption
  }

  def zipMap[A,B,T](a:Option[A], b:Option[B])(lambda:(A,B) => T):Option[T] = {
    (a,b).zipped.map { case (a,b) => lambda(a,b) }.headOption
  }

  def zipMap[A,B,C,T](a:Option[A], b:Option[B], c:Option[C])(lambda:(A,B,C) => T):Option[T] = {
    (a,b,c).zipped.map { case (a,b,c) => lambda(a,b,c) }.headOption
  }

  def zipReduce[A](a:Option[A], b:Option[A])(lambda:(A,A)=>A):Option[A] = {
    (a, b) match {
      case (Some(a), Some(b)) => Some(lambda(a,b))
      case (Some(a), None) => Some(a)
      case (None, Some(b)) => Some(b)
      case (None, None) => None
    }
  }

  def reverseMap[K,V](map:scala.collection.Map[K,V]):Map[V,Set[K]] = {
    map.groupBy(_._2).mapValues(_.keys.toSet)
  }

  def reverseOneToOneMap[K,V](map:scala.collection.Map[K,V]):Map[V,K] = {
    reverseMap(map).map { case (v, ks) => v -> assertIdentical(ks, "name").get }
  }

  def flatReduce[A](list:List[Option[A]])(lambda:(A,A) => A):Option[A] = {
    list.reduce[Option[A]]{
      case (Some(a), Some(b)) => Some(lambda(a,b))
      case _ => None
    }
  }

  /*
   * If a and b can be reduced, reduce return Some(c) else None
   * */
  def partialReduce[A](list:List[A])(reduce:(A,A) => Option[A]):List[A] = {
    val queue = scala.collection.mutable.ListBuffer[A]()
    val reduced = scala.collection.mutable.Queue[A]()
    queue ++= list
    while (queue.nonEmpty) {
      val a = queue.remove(0)
      val cs = queue.flatMap { b => reduce(a,b).map { c => (b,c) } }
      if (cs.isEmpty) reduced += a
      else {
        cs.foreach { case (b,c) =>
          queue -= b
          queue +=c
        }
      }
    }
    reduced.toList
  }

  def unpack(x:Any)(base:PartialFunction[Any,Any]):Any = {
    def recurse(x:Any):Any = x match {
      case x:Iterable[_] => x.map { recurse }
      case x:Option[_] => x.map { recurse }
      case (a,b) => (recurse(a), recurse(b))
      case (a,b,c) => (recurse(a), recurse(b), recurse(c))
      case (a,b,c,d) => (recurse(a), recurse(b), recurse(c), recurse(d))
      case x if base.isDefinedAt(x) => 
        base(x)
      case x => x
    }
    recurse(x)
  }

  implicit class AnyUtil(x:Any) {
    def to[T:ClassTag]:Option[T] = x match {
      case x:T => Some(x)
      case _ => None
    }
    def as[T]:T = x.asInstanceOf[T]
    def newInstance[T](args:Seq[Any]):T = x.getClass.newInstanceAs[T](args)
  }

  implicit class ClassHelper(cl:Class[_]) {
    def newInstanceAs[T](args:Seq[Any]):T = {
      val constructor = cl.getConstructors()(0) 
      try {
        constructor.newInstance(args.map(_.asInstanceOf[Object]):_*).asInstanceOf[T]
      } catch {
        case e:java.lang.IllegalArgumentException =>
          err(s"Error during newInstance of node $cl", exception=false)
          val expected = constructor.getParameterTypes()
          val got = args.map(_.getClass)
          err(s"Expected (${expected.size}):", exception=false)
          expected.foreach { e =>
            err(s"$e", exception=false)
          }
          err(s"Got (${got.size}):", exception=false)
          got.foreach { e =>
            err(s"$e", exception=false)
          }
          throw e
        case e:java.lang.reflect.InvocationTargetException =>
          err(s"InvocationTargetException during newInstance of node $this", exception=false)
          err(s"args=$args", exception=false)
          err(s"Cause:", exception=false)
          err(s"${e.getCause}", exception=false)
          throw e
        case e:Throwable => throw e
      }
    }
  }

  implicit class TUtil[T](x:T) {
    def foldAt[A](l:scala.collection.GenTraversableOnce[A])(func:(T, A) => T) = l.foldLeft[T](x) { (x, l) => func(x, l) }
  }

  implicit class LongOp(i:Long) {
    // Round up division
    def /! (d:Long) = (i + d - 1) / d
  }

  implicit class IntOp(i:Int) {
    // Round up division
    def /! (d:Int) = (i + d - 1) / d
  }

  implicit class NumOp[N:Numeric](i:N) {
    // Round up division
    def /! (d:N):N = ((i, d) match {
      case (i:Int,d:Int) => (i + d - 1) / d
      case (i:Long,d:Long) => (i + d - 1) / d
      case (i:Double,d:Double) => (i / d).ceil
      case (i:Float,d:Float) => (i / d).ceil
      case (_,_) => throw PIRException(s"Don't know how to do roundup div")
    }).as[N]

    def log2:N = i match {
      case i:Int => ((math.log(i) / math.log(2)).ceil).toInt.as[N]
      case i:Long => ((math.log(i) / math.log(2)).ceil).toLong.as[N]
      case i:Float => (math.log(i) / math.log(2)).toFloat.as[N]
      case i:Double => (math.log(i) / math.log(2)).toDouble.as[N]
    }
  }

  implicit class Tuple2Util[A,B](tuple:(A,B)) {
    def map1[C](func:A => C) = (func(tuple._1), tuple._2)
    def map2[C](func:B => C) = (tuple._1, func(tuple._2))
  }

  implicit class OrderedIterUtil[T:Ordering](x:Iterable[T]) {
    def maxOption:Option[T] = if (x.isEmpty) None else Some(x.max)
  }
  implicit class IterUtil[T](x:Iterable[T]) {
    def maxOptionBy[O:Ordering](func:T => O):Option[T] = 
      if (x.isEmpty) None else Some(x.maxBy(func))
    def minOptionBy[O:Ordering](func:T => O):Option[T] = 
      if (x.isEmpty) None else Some(x.minBy(func))
    def tryFilter(lambda:T => Boolean):Iterable[T] = {
      val filtered = x.filter(lambda)
      if (filtered.isEmpty) x else filtered
    }
  }
  implicit class SetUtil[T](s:Set[T]) {
    def contains(x:Any) = s.contains(x.asInstanceOf[T])
  }
  implicit class OptionOp[A](o:Option[A]) {
    def toValue:Value[A] = o match {
      case None => Unknown
      case Some(v) => Finite(v)
    }
  }

  def log2(x:Int) = (Math.log(x) / Math.log(2)).ceil.toInt

  final val SINGLE_PRECISION = 32

  case class MatchRule[A:ClassTag, B](lambda:A => Option[B]) {
    def unapply(x:Any):Option[B] = x match {
      case x:A => lambda(x)
      case _ => None
    }
  }

}
