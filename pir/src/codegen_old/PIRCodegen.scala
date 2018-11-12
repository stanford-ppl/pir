package pir
package codegen

import pir.pass._

trait PIRCodegen extends DFSTopDownTopologicalTraversal with Codegen {

  val forward = true

}
