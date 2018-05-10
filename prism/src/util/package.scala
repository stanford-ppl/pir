package prism

import scala.collection.mutable.Stack

package object util extends prism.codegen.Printer  {
  val times = Stack[Long]()
  openStdout
}

