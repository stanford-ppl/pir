package prism

import scala.collection.mutable.Stack

package object util extends prism.codegen.Printer with FileManager with Misc with ScalaAlias with Serialization {
  type Logging = prism.codegen.Logging

  val times = Stack[Long]()
  openStdout
}

