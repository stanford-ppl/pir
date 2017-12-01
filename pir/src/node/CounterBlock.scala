package pir.node

import pir._
import pir.util._
import pir.pass.ForwardRef

import pirc._
import pirc.enums._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

trait CounterBlock { self:ComputeUnit =>

  def reset = {}

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

  // Return Some(cchain) if new copy is made
  def getCopy(cchain:CounterChain, logger:Option[Logger]=None):Option[CounterChain] = {
    if (cchainMap.contains(cchain)) None else {
      val cp = CounterChain.copy(cchain.original, logger)
      logger.foreach { _.dprintln(s"Creating new copy=$cp of $cchain(${cchain.ctrler}) in $this") }
      cchainMap += (cchain.original) -> cp
      Some(cp)
    }
  }

  def cloneCC(cchain:CounterChain, logger:Option[Logger]):CounterChain = {
    CounterChain.clone(cchain.original, logger)
  }

  def containsCopy(cchain:CounterChain):Boolean = {
    cchainMap.contains(cchain.original)
  }
  
}
