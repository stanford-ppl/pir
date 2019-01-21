package prism

import prism.util._
import prism.mapper._
import prism.collection.immutable._

trait PrismLocalAlias extends Misc with MappingUtil with ScalaUtil with FileManager with Serialization {
  
  type Env = prism.graph.BuildEnvironment

  type PIRException = prism.exceptions.PIRException
  val PIRException = prism.exceptions.PIRException

  type Codegen = prism.codegen.Codegen
  type Printer = prism.codegen.Printer
  type Logging = prism.codegen.Logging

  type MappingFailure = prism.mapper.MappingFailure

  //type Metadata = prism.util.Metadata
  val ConsoleLogger=prism.util.ConsoleLogger

  type ND = prism.graph.Node[X] forSome { type X <:prism.graph.Node[X] }
  type E = prism.graph.Edge
  type FG[K,V,S<:FactorGraphLike[K,V,S]] = FactorGraphLike[K,V,S]
}

trait PrismAlias extends PrismLocalAlias {
  type Design = prism.graph.Design
  type Pass = prism.Pass
  type Compiler = prism.Compiler
}
