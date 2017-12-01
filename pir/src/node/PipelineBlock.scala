package pir.node

import pir._

import scala.collection.mutable.ListBuffer

trait PipelineBlock extends Node {

  var stageIndex = 0
  def nextIndex = { val temp = stageIndex; stageIndex +=1 ; temp}

  val _rdAddrStages = ListBuffer[RAStage]()
  def rdAddrStages = _rdAddrStages.toList
  val _wtAddrStages = ListBuffer[WAStage]()
  def wtAddrStages = _wtAddrStages.toList
  val _localStages = ListBuffer[LocalStage]()
  def localStages = _localStages.toList 

  def reset =  { _localStages.clear; _wtAddrStages.clear; _rdAddrStages.clear }

  def stages:List[Stage] = localStages 

  def addStage(stage:Stage):Unit = { 
    stage.index(nextIndex)
    val pool = stage match {
      case stage:WAStage => _wtAddrStages
      case stage:RAStage => _rdAddrStages
      case stage:LocalStage => _localStages
    }
    pool.lastOption.foreach { prev =>
      stage.prev = Some(prev)
      prev.next = Some(stage)
    }
    stage match {
      case stage:WAStage => _wtAddrStages += stage
      case stage:RAStage => _rdAddrStages += stage
      case stage:LocalStage => _localStages += stage
    }
  }
}
