package prism

import macros._

import scala.language.implicitConversions
import scala.collection.mutable.Stack
import java.lang.Thread

package object util extends Printer with Serialization with FileManager with Reflection with Misc {
  type Logging = prism.codegen.Logging

  val times = Stack[Long]()
  openStdout
}

