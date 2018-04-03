package pir

package object mapper extends prism.mapper.Alias with MappingLogger {
  type PNode = pir.node.PIRNode
  type SNode = spade.node.SpadeNode
  type PIRPass = pir.pass.PIRPass
  type PIRMetadata = pir.util.PIRMetadata
  type ClassTag[T] = scala.reflect.ClassTag[T]
  def classTag[T](implicit ctag: ClassTag[T]) = scala.reflect.classTag[T]
  type TypeTag[T] = scala.reflect.runtime.universe.TypeTag[T]
  def typeTag[T](implicit ctag: TypeTag[T]) = scala.reflect.runtime.universe.typeTag[T]
  def typeOf[T](implicit ctag: TypeTag[T]) = scala.reflect.runtime.universe.typeOf[T]

  type Logging = prism.codegen.Logging

  type FIMap = spade.config.FIMap
  val FIMap = spade.config.FIMap
  type ConfigMap = spade.config.ConfigMap
  val ConfigMap = spade.config.ConfigMap
  type SpadeMapLike = spade.config.SpadeMapLike

  val OneToManyMap = prism.collection.immutable.OneToManyMap
  type OneToManyMap[K,V] = prism.collection.immutable.OneToManyMap[K,V]
  type FactorGraphLike[K,V,S] = prism.collection.immutable.FactorGraphLike[K,V,S]
  val InvalidFactorGraph = prism.collection.immutable.InvalidFactorGraph
}
