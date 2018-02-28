
package object pirc {
  /* ------------- Alias ------------- **/

  type Design = prism.node.Design

  /* codegen */
  //type Codegen = pirc.codegen.Codegen
  type Printer = prism.codegen.Printer
  type Logging = prism.codegen.Logging

  /* pass */

  /* exceptions */
  type PIRException = pirc.exceptions.PIRException
  val PIRException = pirc.exceptions.PIRException

  /* ------------- Alias (END) ------- **/
}
