package prism

import prism.util._

package object node extends Misc with ScalaAlias {
  type Pass = prism.Pass
  type Runner[P<:Pass] = prism.Runner[P]
  val Runner = prism.Runner
  type Compiler = prism.Compiler
  val Config = prism.Config
  type Logging = prism.codegen.Logging
  type Metadata = prism.util.Metadata

  type Serializable = prism.util.Serializable
}
