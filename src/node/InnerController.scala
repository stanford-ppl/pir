package pir.node

import pir._

import scala.collection.mutable.ListBuffer

abstract class InnerController(name:Option[String])(implicit design:PIR) extends ComputeUnit(name)
 with InnerRegBlock {
  import pirmeta._

  /* Stages */
  val _rdAddrStages = ListBuffer[RAStage]()
  def rdAddrStages = _rdAddrStages.toList
  val _wtAddrStages = ListBuffer[WAStage]()
  def wtAddrStages = _wtAddrStages.toList
  val _localStages = ListBuffer[LocalStage]()
  def localStages = _localStages.toList 

  //override def stages = (emptyStage :: wtAddrStages ++ rdAddrStages ++ localStages).toList
  override def stages:List[Stage] = localStages

  def addStage(s:Stage):Unit = { 
    indexOf(s) = nextIndex
    val pool = s match {
      case s:WAStage => wtAddrStages
      case s:RAStage => rdAddrStages
      case s:LocalStage => localStages
    }
    pool.lastOption.foreach { prev =>
      s.prev = Some(prev)
      prev.next = Some(s)
    }
    s match {
      case s:LocalStage => _localStages += s
      case s:WAStage => _wtAddrStages += s
      case s:RAStage => _rdAddrStages += s 
    }
  }

  /* Controller Hierarchy */
  def locals = this :: outers
  /* List of outer controllers reside in current inner*/
  var outers:List[OuterController] = Nil

  /* Block updates */
  override def reset =  { super.reset; _localStages.clear; _wtAddrStages.clear; _rdAddrStages.clear }

}
