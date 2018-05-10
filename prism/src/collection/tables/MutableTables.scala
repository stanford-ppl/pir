package prism
package collection.mutable

import prism.collection._

import scala.collection.mutable
import scala.collection.immutable

class Table[C, V, E](
  val values:Map[C,List[V]], 
  val default:Option[E]=None,
  val map:mutable.Map[immutable.Map[C,V],E]=mutable.Map[immutable.Map[C,V],E]()
) extends prism.collection.Table[C,V,E] {
  def this(map:mutable.Map[Map[C,V],E]) = this(Table.extractValues(map),None,map)
  def update(xs:Any*):Unit = {
    val (cols,e) = xs.splitAt(xs.length-1)
    val keys:List[K] = toKey(cols:_*) 
    keys.foreach { k => map(k) = e.head.asInstanceOf[E] }
  }
}

object Table {
  def apply[C,V,E](values:Map[C,List[V]], default:E) = { 
    new Table[C,V,E](values=values, default=Some(default))
  }
  def extractValues[C,V,E](map:mutable.Map[Map[C,V],E]):Map[C,List[V]] = {
    map.keys.flatMap(_.toSeq).toList.groupBy(_._1).map{ case (k, list) => (k, list.map(_._2)) }
  }
}

