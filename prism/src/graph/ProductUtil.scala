package prism
package graph

trait ProductUtil {
  import scala.reflect.runtime.universe.{TypeTag=>_,typeOf=>_,_}

  def caseFieldNames[T:TypeTag] = typeOf[T].members.sorted.collect {
    case m: MethodSymbol if m.isCaseAccessor => m.name.toString
  }.toList

  implicit class ProductHelper[P<:Product:TypeTag](p:P) {
    def fieldNames:List[String] = caseFieldNames[P]
    def fields:List[(String, Any)] = {
      fieldNames.zip(p.productIterator.toList)
    }
  }
}
