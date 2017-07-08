package pir.util.tables.mutable

import scala.collection._

class Table[C, V, E](val values:immutable.Map[C,List[V]], val default:Option[E]=None) extends pir.util.tables.Table[C,V,E] {
  val map:mutable.Map[K,E] = mutable.Map.empty
  def update(xs:Any*):Unit = {
    val (cols,e) = xs.splitAt(xs.length-1)
    val keys:List[K] = toKey(cols:_*) 
    keys.foreach { k => map(k) = e.head.asInstanceOf[E] }
  }
}

object Table {
  def apply[C,V,E](values:immutable.Map[C,List[V]], default:E) = { 
    new Table[C,V,E](values, default=Some(default))
  }
}

