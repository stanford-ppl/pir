package prism
package util

object Value {

  import scala.language.implicitConversions

  /** An implicit conversion that converts an value to an iterable value
   */
  implicit def value2Iterable[A](xo: Value[A]): Iterable[A] = xo.toList

  implicit def option2value[A](o:Option[A]): Value[A] = o match {
    case None => Unknown
    case Some(v) => Finite(v)
  }

  implicit def A2value[A](a:A): Value[A] = Finite(a)

  implicit def value2valueLong[A:Numeric](v:Value[A]): Value[Long] = v.map { implicitly[Numeric[A]].toLong(_) } 

  implicit class ValueNumOp[A:Numeric](t:Value[A]) {
    val num = implicitly[Numeric[A]]
    def + (v:Value[A]):Value[A] = t.zipMap(v) { (t,v) => num.plus(t,v) }

    def - (v:Value[A]) = { 
      v match {
        case Infinite => bug(s"Subtract by Infinite")
        case v => t.zipMap(v) { (t,v) => num.minus(t,v) }
      }
    }

    def * (v:Value[A]):Value[A] = t.zipMap(v) { (t,v) => num.times(t,v) }

    def /! (v:Value[A]):Value[A] = {
      v match {
        case Infinite => Unknown
        case v => t.zipMap(v) { (t,v) => t /! v }
      }
    }

  }

  /** An Value factory which creates Finite(x) if the argument is not null,
   *  and Unknown if it is null.
   *
   *  @param  x the value
   *  @return   Finite(value) if value != null, Unknown if value == null
   */
  def apply[A:Numeric](x: A): Value[A] = if (x == null) Unknown else Finite(x)

  /** An Value factory which returns `Unknown` in a manner consistent with
   *  the collections hierarchy.
   */
  def empty[A] : Value[A] = Unknown
}

@SerialVersionUID(-114498752079829388L) 
sealed abstract class Value[+A] extends Product with Serializable {
  self =>

  def isKnown: Boolean
  def unknown: Boolean = !isKnown
  def isFinite: Boolean = this.isInstanceOf[Finite[_]]

  /** Returns true if the value is $none, false otherwise.
   */
  def isEmpty: Boolean

  /** Returns true if the value is an instance of $some, false otherwise.
   */
  def isDefined: Boolean = !isEmpty

  /** Returns the value's value.
   *  @note The value must be nonempty.
   *  @throws java.util.NoSuchElementException if the value is empty.
   */
  def get: A

  /** Returns the value's value if the value is nonempty, otherwise
   * return the result of evaluating `default`.
   *
   *  @param default  the default expression.
   */
  @inline final def getOrElse[B >: A](default: => B): B =
    if (isEmpty) default else this.get

  /** Returns the value's value if it is nonempty,
   * or `null` if it is empty.
   * Although the use of null is discouraged, code written to use
   * $value must often interface with code that expects and returns nulls.
   * @example {{{
   * val initialText: Value[String] = getInitialText
   * val textField = new JComponent(initialText.orNull,20)
   * }}}
   */
  @inline final def orNull[A1 >: A](implicit ev: Null <:< A1): A1 = this getOrElse ev(null)

  /** Returns a $some containing the result of applying $f to this $value's
   * value if this $value is nonempty.
   * Otherwise return $none.
   *
   *  @note This is similar to `flatMap` except here,
   *  $f does not need to wrap its result in an $value.
   *
   *  @param  f   the function to apply
   *  @see flatMap
   *  @see foreach
   */
  @inline final def map[B:Numeric](f: A => B): Value[B] =
    if (isEmpty) Unknown else Finite(f(this.get))

  /** Returns the result of applying $f to this $value's
   *  value if the $value is nonempty.  Otherwise, evaluates
   *  expression `ifEmpty`.
   *
   *  @note This is equivalent to `$value map f getOrElse ifEmpty`.
   *
   *  @param  ifEmpty the expression to evaluate if empty.
   *  @param  f       the function to apply if nonempty.
   */
  @inline final def fold[B](ifEmpty: => B)(f: A => B): B =
    if (isEmpty) ifEmpty else f(this.get)

  /** Returns the result of applying $f to this $value's value if
   * this $value is nonempty.
   * Returns $none if this $value is empty.
   * Slightly different from `map` in that $f is expected to
   * return an $value (which could be $none).
   *
   *  @param  f   the function to apply
   *  @see map
   *  @see foreach
   */
  @inline final def flatMap[B](f: A => Value[B]): Value[B] =
    if (isEmpty) Unknown else f(this.get)

  def flatten[B](implicit ev: A <:< Value[B]): Value[B] =
    if (isEmpty) Unknown else ev(this.get)

  /** Returns this $value if it is nonempty '''and''' applying the predicate $p to
   * this $value's value returns true. Otherwise, return $none.
   *
   *  @param  p   the predicate used for testing.
   */
  @inline final def filter(p: A => Boolean): Value[A] =
    if (isEmpty || p(this.get)) this else Unknown

  /** Returns this $value if it is nonempty '''and''' applying the predicate $p to
   * this $value's value returns false. Otherwise, return $none.
   *
   *  @param  p   the predicate used for testing.
   */
  @inline final def filterNot(p: A => Boolean): Value[A] =
    if (isEmpty || !p(this.get)) this else Unknown

  /** Returns false if the value is $none, true otherwise.
   *  @note   Implemented here to avoid the implicit conversion to Iterable.
   */
  final def nonEmpty = isDefined

  /** Tests whether the value contains a given value as an element.
   *
   *  @example {{{
   *  // Returns true because Finite instance contains string "something" which equals "something".
   *  Finite("something") contains "something"
   *
   *  // Returns false because "something" != "anything".
   *  Finite("something") contains "anything"
   *
   *  // Returns false when method called on Unknown.
   *  Unknown contains "anything"
   *  }}}
   *
   *  @param elem the element to test.
   *  @return `true` if the value has an element that is equal (as
   *  determined by `==`) to `elem`, `false` otherwise.
   */
  final def contains[A1 >: A](elem: A1): Boolean =
    !isEmpty && this.get == elem

  /** Returns true if this value is nonempty '''and''' the predicate
   * $p returns true when applied to this $value's value.
   * Otherwise, returns false.
   *
   *  @param  p   the predicate to test
   */
  @inline final def exists(p: A => Boolean): Boolean =
    !isEmpty && p(this.get)

  /** Returns true if this value is empty '''or''' the predicate
   * $p returns true when applied to this $value's value.
   *
   *  @param  p   the predicate to test
   */
  @inline final def forall(p: A => Boolean): Boolean = isEmpty || p(this.get)

  /** Apply the given procedure $f to the value's value,
   *  if it is nonempty. Otherwise, do nothing.
   *
   *  @param  f   the procedure to apply.
   *  @see map
   *  @see flatMap
   */
  @inline final def foreach[U](f: A => U) {
    if (!isEmpty) f(this.get)
  }

  /** Returns this $value if it is nonempty,
   *  otherwise return the result of evaluating `alternative`.
   *  @param alternative the alternative expression.
   */
  @inline final def orElse[B >: A](alternative: => Value[B]): Value[B] =
    if (isEmpty) alternative else this

  /** Returns a singleton iterator returning the $value's value
   * if it is nonempty, or an empty iterator if the value is empty.
   */
  def iterator: Iterator[A] =
    if (isEmpty) scala.collection.Iterator.empty else scala.collection.Iterator.single(this.get)

  /** Returns a singleton list containing the $value's value
   * if it is nonempty, or the empty list if the $value is empty.
   */
  def toList: List[A] =
    if (isEmpty) List() else new ::(this.get, Nil)

  def zip[B](v:Value[B]) = (this, v) match {
    case (Unknown,_) => None
    case (_,Unknown) => None
    case (t,v) => Some(t,v)
  }

  def zipMap[B, C](v:Value[B])(f:(A,B) => C):Value[C] = (this, v) match {
    case (Unknown, _) => Unknown
    case (_, Unknown) => Unknown
    case (Infinite, _) => Infinite
    case (_, Infinite) => Infinite
    case (Finite(t), Finite(v)) => Finite(f(t, v))
    case (_,_) => Unknown
  }

}

@SerialVersionUID(1234815782226070388L) // value computed by serialver for 2.11.2, annotation added in 2.11.4
final case class Finite[+A](@deprecatedName('x, "2.12.0") value: A) extends Value[A] {
  def isEmpty = false
  def isKnown = true
  def get = value
}

@SerialVersionUID(5066590221178148012L) // value computed by serialver for 2.11.2, annotation added in 2.11.4
case object Unknown extends Value[Nothing] {
  def isEmpty = true
  def isKnown = false
  def get = throw new NoSuchElementException("Unknown.get")
}
case object Infinite extends Value[Nothing] {
  def isEmpty = true
  def isKnown = true
  def get = throw new NoSuchElementException("Unknown.get")
}

