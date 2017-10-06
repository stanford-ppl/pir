package pir.node

import pir._
import pir.util._
import pir.pass.ForwardRef

import pirc._
import pirc.enums._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

abstract class ComputeUnit(implicit design: PIR) extends Controller with OuterRegBlock {
  override val typeStr = "CU"
  import pirmeta._

  /* Fields */
  /* CounterChains */
  val cchainMap = Map[CounterChain, CounterChain]() // map between original and copied cchains
  def cchains = cchainMap.values.toList
  def addCChain(cc:CounterChain):CounterChain = {
    if (!cc.isDefined) return cc // If cc is a copy but haven't been updated, addCChain during update 
    if (cchainMap.contains(cc.original) && cchainMap(cc.original)!=cc)
      throw PIRException(s"Already have copy/original copy of ${cc.original} but adding duplicated copy ${cc}")
    else cchainMap += (cc.original -> cc)
    return cc
  }
  def removeCChain(cc:CounterChain):Unit = {
    cchainMap.get(cc.original).foreach { cp => if (cp== cc) cchainMap -= cc.original }
  }
  def removeCChainCopy(cc:CounterChain):Unit = { 
    assert(!cc.isCopy)
    cchainMap -= cc
  }

  def getCC(cchain:CounterChain):CounterChain = cchainMap(cchain.original)

  def getCopy(cchain:CounterChain)(implicit logger:Logger):CounterChain = {
    val copy = cchainMap.getOrElseUpdate(cchain.original, {
      val cp = CounterChain.copy(cchain.original)(this, design)
      logger.dprintln(s"Creating new copy=$cp of $cchain in $this")
      cp
    })
    assert(cchainMap.contains(copy.original))
    copy
  }

  def cloneCC(cchain:CounterChain):CounterChain = {
    val clone = CounterChain.clone(cchain.original)(this, design)
    assert(cchainMap.contains(clone.original)) // clone's original should be it self
    clone
  }

  def containsCopy(cchain:CounterChain):Boolean = {
    cchainMap.contains(cchain.original)
  }
  
  override def toUpdate = { super.toUpdate }

  var index = 0
  def nextIndex = { val temp = index; index +=1 ; temp}

  //val emptyStage = EmptyStage(); indexOf(emptyStage) = nextIndex 
  //def stages:List[Stage] = emptyStage :: Nil 
  def stages:List[Stage] = Nil 

}
