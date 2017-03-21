package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

trait OuterRegBlock { self:ComputeUnit =>

  var regId = 0
  def newTemp = {val temp = regId; regId +=1; temp}

  var infGraph:Map[Reg, Set[Reg]] = Map.empty

  val scalarInRegs  = Map[ScalarIn, ScalarInPR]()
  val loadRegs   = Map[OnChipMem, LoadPR]()
  def reset      = { regId = 0; loadRegs.clear; scalarInRegs.clear }

  def scalarInPR(s:ScalarIn):ScalarInPR = scalarInRegs.getOrElseUpdate(s, ScalarInPR(newTemp, s))
  def loadPR(s:OnChipMem):LoadPR = loadRegs.getOrElseUpdate(s, LoadPR(newTemp, s))

  def pipeReg(stage:Stage, reg:Reg) = stage.getOrElseUpdate(reg, PipeReg(stage,reg))

 /** Create a pipeline register for a stage corresponding to 
  *  the register that loads from the sram
  * @param stage: Stage of the pipeline register 
  * @param s: sram to load from 
  */
 def load(stage:Stage, s:OnChipMem):PipeReg = pipeReg(stage, loadPR(s))

 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarIn buffer with register rid
  * @param stage: Stage of the pipeline register 
  * @param s: ScalarIn buffer 
  */
  def scalarIn(stage:Stage, s:ScalarIn):PipeReg = {
    //val fifo = getRetimingFIFO(s.scalar) 
    //fifo.wtPort(s.out)
    //stage match {
      //case stage:EmptyStage => fifo.load
      //case stage => load(stage, fifo).out
    //}
    pipeReg(stage, scalarInPR(s)) // ScalarBuffer or ScalarFIFO should be inserted here
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarIn buffer with register rid
  * @param stage: Stage of the pipeline register 
  * @param rid: reg rid of scalar input 
  */
  def scalarIn(stage:Stage, s:Scalar):PipeReg = scalarIn(stage, newSin(s))
  def scalarIn(s:Scalar):PipeReg = scalarIn(emptyStage, newSin(s))
  /** Create a ScalarOut object 
  * @param s: scalar value 
  */
}

trait InnerRegBlock extends OuterRegBlock { self:InnerController =>

  /* Register Mapping */
  lazy val reduceReg  = ReducePR(newTemp)
  val scalarOutRegs = Map[ScalarOut, ScalarOutPR]()
  val vecInRegs     = Map[VecIn, VecInPR]()
  val vecOutRegs     = Map[VecOut, VecOutPR]()
  val storeRegs  = Map[OnChipMem, StorePR]()
  val wtAddrRegs = Map[SRAMOnWrite, WtAddrPR]()
  //val rdAddrRegs = Map[SRAM, RdAddrPR]()
  val ctrRegs    = Map[Counter, CtrPR]()
  val tempRegs   = Set[Reg]()
  val accumRegs  = Set[AccumPR]()
  override def reset      = { super.reset; storeRegs.clear; ctrRegs.clear}

  def storePR(s:OnChipMem):StorePR = storeRegs.getOrElseUpdate(s, StorePR(newTemp, s))

  def wtAddrPR(s:SRAMOnWrite):WtAddrPR = wtAddrRegs.getOrElseUpdate(s, WtAddrPR(newTemp, s.writeAddr))

  def ctrPR(c:Counter):CtrPR = ctrRegs.getOrElseUpdate(c, CtrPR(newTemp, c))

  def accumPR(init:Const[_<:AnyVal]):AccumPR = {
    val acc = AccumPR(newTemp, init)
    accumRegs += acc 
    acc
  }

  def scalarOutPR(s:ScalarOut):ScalarOutPR = scalarOutRegs.getOrElseUpdate(s, ScalarOutPR(newTemp, s))

  def vecInPR(vo:VecIn):VecInPR = vecInRegs.getOrElseUpdate(vo, VecInPR(newTemp, vo))

  def vecOutPR(vo:VecOut):VecOutPR = vecOutRegs.getOrElseUpdate(vo, VecOutPR(newTemp, vo))

  def tempPR():Reg = {
    val reg = Reg(newTemp)
    tempRegs += reg 
    reg
  }

 /** Create a pipeline register for a stage corresponding to 
  *  the register that stores to the sram
  * @param stage: Stage of the pipeline register 
  * @param s: sram to load from 
  */
  def store(stage:Stage, s:OnChipMem):PipeReg = pipeReg(stage, storePR(s))

  def wtAddr(sram:SRAMOnWrite):WtAddrPR = wtAddrPR(sram)
  def wtAddr(stage:Stage, reg:WtAddrPR):PipeReg = pipeReg(stage, reg)
  def wtAddr(stage:Stage, sram:SRAMOnWrite):PipeReg = pipeReg(stage, wtAddr(sram))
  
  def ctr(c:Counter):PipeReg = pipeReg(emptyStage, ctrPR(c))
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the counter 
  * @param stage: Stage of the pipeline register 
  * @param c: counter 
  */
  def ctr(stage:Stage, c:Counter):PipeReg = pipeReg(stage, ctrPR(c))
  /* Create a new logical accumulator register */
  def accum(init:Const[_<:AnyVal]):AccumPR = accumPR(init)
  /* Create a new logical accumulator register and return a PipeReg for the stage and the created
   * accumulator 
  * @param stage: Stage of the pipeline register 
   * @param init initial value of the accumulator
   * */
  def accum(stage:Stage, init:Const[_<:AnyVal]):PipeReg = pipeReg(stage, accum(init))
  /* Create a pipeline register for a stage that connects to the accumulator reg 
   * @param stage
   * @param acc 
   */
  def accum(stage:Stage, acc:AccumPR):PipeReg = pipeReg(stage, acc)
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the reduction network 
  * @param stage: Stage of the pipeline register 
  * @param i: initial value
  */
  def reduce(stage:Stage):PipeReg = pipeReg(stage, reduceReg)

 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarOut buffer
  * @param stage: Stage of the pipeline register 
  */
  def scalarOut(stage:Stage, s:ScalarOut):PipeReg = pipeReg(stage, scalarOutPR(s))
 /** Create a pipeline register and a scalar buffer for a stage. 
  *  The pipeline register connects to the scalarOut buffer
  * @param stage: Stage of the pipeline register 
  */
  def scalarOut(stage:Stage, s:Scalar):PipeReg = scalarOut(stage, newSout(s))
  def scalarOut(s:Scalar):PipeReg = scalarOut(emptyStage, newSout(s))
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU input ports in streaming communication 
  * @param stage: Stage of the pipeline register 
  */
  def vecIn(stage:Stage, v:VecIn):PipeReg = {
    //val fifo = getRetimingFIFO(v.vector) 
    //fifo.wtPort(v.out)
    //stage match {
      //case stage:EmptyStage => fifo.load
      //case stage => load(stage, fifo).out
    //}
    pipeReg(stage, vecInPR(v))
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU input ports in streaming communication 
  * @param stage: Stage of the pipeline register 
  */
  def vecIn(stage:Stage, vec:Vector):PipeReg = vecIn(stage, newVin(vec))
  def vecIn(vec:Vector):PipeReg= vecIn(emptyStage, newVin(vec))
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU output ports 
  * @param stage: Stage of the pipeline register 
  * @param vo: VecOut of current ComputeUnit. One per CU 
  */
  def vecOut(stage:Stage, vo:VecOut):PipeReg = pipeReg(stage, vecOutPR(vo))
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU output ports 
  * @param stage: Stage of the pipeline register 
  */
  def vecOut(stage:Stage, vec:Vector):PipeReg = vecOut(stage, newVout(vec))
  def vecOut(vec:Vector):PipeReg = vecOut(emptyStage, newVout(vec))

  /* Create a new logical register 
   * */
  def temp = tempPR() 

 /** Refer to the pipeline register for stage with rid 
  * @param stage: Stage of the pipeline register 
  */
  def temp(stage:Stage, reg:Reg):PipeReg = {
    assert(tempRegs.contains(reg), s"PipeReg with reg:${reg} wans't created but try to refer to it")
    pipeReg(stage, reg)
  }
 /** Allocate a new pipeline register in the stage 
  * @param stage: Stage of the pipeline register 
  */
  def temp(stage:Stage):PipeReg = {
    pipeReg(stage, tempPR())
  }
}

