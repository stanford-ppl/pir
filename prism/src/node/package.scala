package prism

import prism.util._

package object node extends Misc with ScalaAlias {
  type Pass = prism.Pass
  type RunPass[P<:Pass] = prism.RunPass[P]
  val RunPass = prism.RunPass
  type Compiler = prism.Compiler
  val Config = prism.Config
  type Logging = prism.codegen.Logging
  type Metadata = prism.util.Metadata

  type Serializable = prism.util.Serializable
}
