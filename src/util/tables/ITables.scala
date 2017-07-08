package pir.util.tables.immutable

import scala.collection._

case class Table[C, V, E](values:immutable.Map[C,List[V]], default:Option[E]=None) extends pir.util.tables.Table[C,V,E] {
  val map:immutable.Map[K,E] = immutable.Map.empty
  def + (xs:Any*)(e:E):Table[C,V,E] = {
    val keys:List[K] = toKey(xs:_*) 
    val mp = map
    new Table[C,V,E](values, default) {
      override val map = keys.foldLeft(mp) { case (pm, key) => pm + (key -> e) }
    }
  }
}
