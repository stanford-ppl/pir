package pirc.collection

import pirc.exceptions._

import scala.collection._

trait Table[C, V, E] {
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
    if (cols.size != columns.size)
      throw PIRException(s"Incorrect number of columns for apply. Given ${cols.size}. Number of columns ${columns.size}")
    val key:K = cols.map(check).toMap
    map.getOrElse(key, default.get)
  }

  def toKey(cols:Any*):List[K] = {
    val pairs = cols.map { _ match {
      case (k, vs:Iterable[_]) => (k, vs.asInstanceOf[Iterable[V]].toList)
      case (k, v) => (k, List(v.asInstanceOf[V]))
    } }.toMap
    val keys:List[K] = columns.foldLeft(List[K](Map.empty)) { case (pmps, col) =>
      val vs = if (pairs.contains(col)) pairs(col) else values(col)
      pmps.flatMap { pmp => vs.map { v => pmp + (col -> v) } }
    }
    keys
  }

  override def toString = "Table: \n" + map.mkString("\n")

}
