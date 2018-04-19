package prism

import prism.util._

package object codegen extends Misc with ScalaAlias with FileManager with Serialization {
  type Pass = prism.Pass
  type Runner[P<:Pass] = prism.Runner[P]
  type Compiler = prism.Compiler
  val Config = prism.Config
  type Metadata = prism.util.Metadata
}
