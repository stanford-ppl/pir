package prism

import prism.util._
import prism.mapper._
import prism.collection.immutable._
import prism.graph._

trait PrismLocalAlias extends Misc with MappingUtil with ScalaUtil with FileIOUtil with Serialization {
  
  type Env = prism.graph.BuildEnvironment

  //type PIRException = prism.exceptions.PIRException
  //val PIRException = prism.exceptions.PIRException
  //val CompileError = prism.exceptions.CompileError

  type Codegen = prism.codegen.Codegen
  type Printer = prism.codegen.Printer
  type Logging = prism.codegen.Logging

  type MappingFailure = prism.mapper.MappingFailure

  //type Metadata = prism.util.Metadata
  val ConsoleLogger=prism.util.ConsoleLogger

  type ND = Node[X] forSome { type X <:Node[X] }
  type E = Edge[X,XA,XB] forSome { type X <:Node[X]; type XA<:Edge[X,XA,XB]; type XB <:Edge[X,XB,XA] }
  type EN[N<:Node[N]] = Edge[N,XA,XB] forSome { type XA<:Edge[N,XA,XB]; type XB <:Edge[N,XB,XA] }
  type FG[K,V,S<:FactorGraphLike[K,V,S]] = FactorGraphLike[K,V,S]
}

trait PrismAlias extends PrismLocalAlias {
  type Design = prism.graph.Design
  type Pass = prism.Pass
  type Compiler = prism.Compiler
}
