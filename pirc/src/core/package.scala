
package object prism {
  /* ------------- Alias ------------- **/

  type Design = prism.node.Design

  /* codegen */
  //type Codegen = prism.codegen.Codegen
  type Printer = prism.codegen.Printer
  type Logging = prism.codegen.Logging

  /* pass */

  /* exceptions */
  type PIRException = prism.exceptions.PIRException
  val PIRException = prism.exceptions.PIRException

  /* ------------- Alias (END) ------- **/
}
