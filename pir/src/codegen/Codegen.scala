package pir.codegen

import pir._

abstract class Codegen(implicit design:PIR) extends Pass with pirc.codegen.Codegen
