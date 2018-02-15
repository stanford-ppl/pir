
package object pirc {
  /* ------------- Alias ------------- **/

  /* codegen */
  //type Codegen = pirc.codegen.Codegen
  type Printer = pirc.codegen.Printer
  type Logger = pirc.codegen.Logger
  type Logging = prism.codegen.Logging

  /* pass */

  /* exceptions */
  type PIRException = pirc.exceptions.PIRException
  val PIRException = pirc.exceptions.PIRException

  /* ------------- Alias (END) ------- **/
}
