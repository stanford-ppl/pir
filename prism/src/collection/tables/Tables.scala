package prism.collection

import prism._
import prism.util._

import scala.collection._

trait Table[C, V, E] extends Serializable {
  type K = scala.collection.immutable.Map[C,V]
  def values:scala.collection.immutable.Map[C, List[V]]
  def columns:Iterable[C] = values.keys
  def map:Map[K, E]
  def default:Option[E]

  def check(x:Any):(C, V) = {
    val (c,v) = x match {
      case (x1, x2) => (x1.asInstanceOf[C], x2.asInstanceOf[V])
    }
    if (!values.contains(c)) throw PIRException(s"column $c is not in $columns")
    if (!values(c).contains(v)) throw PIRException(s"column value $v is not in ${values(c)}")
    (c,v)
  }

  def apply(cols:(C,V)*):E = {
    assert(cols.size == columns.size, 
      s"Incorrect number of columns for apply. Given ${cols.size}. Number of columns ${columns.size}")
    val key:K = cols.map(check).toMap
    map.getOrElse(key, default.get)
  }

  def toKey(cols:Any*):List[K] = {
    val pairs = cols.map { _ match {
      case (k, vs:Iterable[_]) => (k, vs.asInstanceOf[Iterable[V]].toList)
      case (k, v) => (k, List(v.asInstanceOf[V]))
    } }.toMap
    pairs.foreach { case (k, vs) => vs.foreach { v => check(k,v) } }
    columns.foldLeft(List[K](Map.empty)) { case (keys, col) =>
      val vs = if (pairs.contains(col)) pairs(col) else values(col)
      keys.flatMap { keys => vs.map { v => keys + (col -> v) } }
    }
  }

  override def toString = "Table: \n" + map.mkString("\n")

}
