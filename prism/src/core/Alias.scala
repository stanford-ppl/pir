package prism

trait Alias {
  type Design = prism.node.Design

  /* codegen */
  type Codegen = prism.codegen.Codegen
  type Printer = prism.codegen.Printer
  type Logging = prism.codegen.Logging

  /* exceptions */
  type PIRException = prism.exceptions.PIRException
  val PIRException = prism.exceptions.PIRException

  /* Scala aliasing */
  type ClassTag[T] = scala.reflect.ClassTag[T]
  def classTag[T](implicit ctag: ClassTag[T]) = scala.reflect.classTag[T]
  type TypeTag[T] = scala.reflect.runtime.universe.TypeTag[T]
  def typeTag[T](implicit ctag: TypeTag[T]) = scala.reflect.runtime.universe.typeTag[T]
  def typeOf[T](implicit ctag: TypeTag[T]) = scala.reflect.runtime.universe.typeOf[T]
}
