
import pir.mapper._
import pir.node._

import scala.language.implicitConversions

package object pir {
  /* ------------- Alias ------------- **/

  /* pass */
  type Pass = pir.pass.Pass

  /* mapper */
  type PIRMap = pir.mapper.PIRMap

  /* util */
  type PIRMetadata = pir.util.PIRMetadata
  /* ------------- Alias (END) ------- **/

  implicit def pr_to_inport(pr:PipeReg):Input = pr.in
  implicit def pr_to_outport(pr:PipeReg):Output = pr.out
  implicit def sram_to_outport(sram:SRAM):Output = sram.readPort
  implicit def ctr_to_port(ctr:Counter):Output = ctr.out
  implicit def const_to_port(const:Const[_<:AnyVal]):Output = const.out
  implicit def mExcep_to_string(e:MappingException[_]):String = e.toString
  implicit def range_to_bound(r:Range)(implicit design:PIR) = r by Const(1) 
  implicit def sRange_to_bound(r:scala.collection.immutable.Range)(implicit design:PIR): (Output, Output, Output) =
    (Const(r.min.toInt).out, Const(r.max.toInt+1).out, Const(r.step.toInt).out)
}
