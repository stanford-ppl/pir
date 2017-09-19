package pirc.collection.mutable

import pirc.collection._

class Table[C, V, E](val values:Map[C,List[V]], val default:Option[E]=None) 
  extends pirc.collection.Table[C,V,E] {
  val map = scala.collection.mutable.Map[K,E]()
  def update(xs:Any*):Unit = {
    val (cols,e) = xs.splitAt(xs.length-1)
    val keys:List[K] = toKey(cols:_*) 
    keys.foreach { k => map(k) = e.head.asInstanceOf[E] }
  }
}

object Table {
  def apply[C,V,E](values:Map[C,List[V]], default:E) = { 
    new Table[C,V,E](values, default=Some(default))
  }
}

