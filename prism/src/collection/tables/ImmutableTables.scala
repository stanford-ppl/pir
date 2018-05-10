package prism
package collection.immutable

import prism.collection._

case class Table[C, V, E](values:Map[C,List[V]], default:Option[E]=None) 
  extends prism.collection.Table[C,V,E] {
  val map:Map[K,E] = Map.empty
  def + (xs:Any*)(e:E):Table[C,V,E] = {
    val keys:List[K] = toKey(xs:_*) 
    val mp = map
    new Table[C,V,E](values, default) {
      override val map = keys.foldLeft(mp) { case (pm, key) => pm + (key -> e) }
    }
  }
}
