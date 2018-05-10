package prism

import prism.util._
import prism.mapper._
import prism.enums._

trait PrismLocalAlias extends Misc with MappingUtil with ScalaAlias with FileManager with Serialization {
  type PIRException = prism.exceptions.PIRException
  val PIRException = prism.exceptions.PIRException

  type Codegen = prism.codegen.Codegen
  type Printer = prism.codegen.Printer
  type Logging = prism.codegen.Logging
  type IRPrinter = prism.codegen.IRPrinter
  type IRDotCodegen = prism.codegen.IRDotCodegen
  type ScalaCodegen = prism.codegen.ScalaCodegen

  type MappingFailure = prism.mapper.MappingFailure

  type Metadata = prism.util.Metadata
  type Serializable = prism.util.Serializable
  val ConsoleLogger=prism.util.ConsoleLogger

  type Op = prism.enums.Op
}

trait PrismAlias extends PrismLocalAlias {
  type Design = prism.node.Design
  type Pass = prism.Pass
  type Compiler = prism.Compiler
  val Config = prism.Config
}
