package pir.node

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.math.max
import pir.PIR
import pir.node._
import pir.util.enums._
import pir.exceptions._
import pir.util._
import scala.reflect.runtime.universe._
import pir.pass.ForwardRef
import pir.util._

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
