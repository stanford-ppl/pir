package prism

import prism.util._

package object codegen extends Misc with ScalaAlias with FileManager with Serialization {
  type Pass = prism.Pass
  type RunPass[P<:Pass] = prism.RunPass[P]
  type Compiler = prism.Compiler
  val Config = prism.Config
  type Metadata = prism.util.Metadata
}
