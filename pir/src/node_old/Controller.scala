package pir
package node

import prism.enums._

abstract class Controller(implicit val design:PIRDesign) extends prism.node.SubGraph[Controller] with IR {
  type P = Controller
  val style:ControlStyle
  val level:ControlLevel
  def isInnerControl = children.isEmpty
  def isOuterControl = !isInnerControl
  def isStream = style==StreamPipe
  val id = design.nextId

  design.ctrlStack.headOption.foreach { c => setParent(c) }
}
case class ForeverController(level:ControlLevel=OuterControl)(implicit design:PIRDesign) extends Controller {
  val style = StreamPipe
}
case class LoopController(style:ControlStyle, level:ControlLevel, cchain:CounterChain)(implicit design:PIRDesign) extends Controller {
  override def className = s"$style"
}
case class UnitController(style:ControlStyle, level:ControlLevel)(implicit design:PIRDesign) extends Controller
case class TopController()(implicit design:PIRDesign) extends Controller {
  val style = MetaPipe
  val level = OuterControl 
}
case class ArgInController()(implicit design:PIRDesign) extends Controller {
  val style = InnerPipe
  val level = InnerControl 
}
case class ArgOutController()(implicit design:PIRDesign) extends Controller {
  val style = InnerPipe
  val level = InnerControl 
}
/*
 * @size constant propogated size in bytes
 * */
case class DramController(size:Option[Int], par:Int)(implicit design:PIRDesign) extends Controller {
  val style = InnerPipe
  val level = InnerControl 
}

sealed trait ControlStyle extends Enum
case object InnerPipe extends ControlStyle
case object SeqPipe extends ControlStyle
case object MetaPipe extends ControlStyle
case object StreamPipe extends ControlStyle
case object ForkSwitch extends ControlStyle
case object ForkJoin extends ControlStyle

sealed trait ControlLevel extends Enum
case object InnerControl extends ControlLevel
case object OuterControl extends ControlLevel

trait ControllerUtil extends prism.traversal.GraphUtil { self:PIRNodeUtil =>

  val pirmeta:PIRMetadata
  import pirmeta._

  def isForever(n:Controller) = n match {
    case n:ForeverController => true
    case n => false
  }

  def localAccessesOf(n:Controller) = {
    ctrlOf.getV(n).getOrElse(Nil).collect { case n:LocalAccess => n }
  }

  def localInMemsOf(n:Controller) = {
    localAccessesOf(n).filter(isOutAccess).flatMap { a => memsOf(a) }.filterNot { m => 
      val mc = ctrlOf(m)
      mc.isDescendentOf(n) || mc == n
    }
  }

  def localOutMemsOf(n:Controller) = {
    localAccessesOf(n).filter(isInAccess).flatMap { a => memsOf(a) }.filterNot { m => 
      val mc = ctrlOf(m)
      mc.isDescendentOf(n) || mc == n
    }
  }
  
  def total[T](n:Controller)(lambda:Controller => Iterable[T]) = {
    (n::n.descendents).flatMap { n => lambda(n) }.toSet
  }

  def accessesOf(n:Controller) = {
    total(n)(localAccessesOf)
  }

  def inMemsOf(n:Controller) = {
    accessesOf(n).filter(isOutAccess).flatMap { a => memsOf(a) }.filterNot { m => 
      val mc = ctrlOf(m)
      mc.isDescendentOf(n) || mc == n
    }
  }

  def outMemsOf(n:Controller) = {
    accessesOf(n).filter(isInAccess).flatMap { a => memsOf(a) }.filterNot { m => 
      val mc = ctrlOf(m)
      mc.isDescendentOf(n) || mc == n
    }
  }

  def foreverOf(ctrl:Controller):Option[Controller] = ctrl match {
    case ctrl:ForeverController => Some(ctrl)
    case ctrl => ctrl.ancestors.filter(isForever).headOption
  }

  // Memories forever controller depends on
  def foreverInMems(ctrl:Controller):List[Memory] = {
    foreverOf(ctrl).toList.flatMap { ctrl => inMemsOf(ctrl).filter { m => isFIFO(m) } }
  }

  // Memories forever controller and current controller depends on
  def myForeverInMems(ctrl:Controller) = {
    (foreverInMems(ctrl).toSet intersect inMemsOf(ctrl).toSet).toList
  }

  // Memories forever controller and current controller (exclude descendents) depends on
  def myLocalForeverInMems(ctrl:Controller) = {
    (foreverInMems(ctrl).toSet intersect localInMemsOf(ctrl).toSet).toList
  }

  def groupByForkJoin[N<:PIRNode](nodes:List[N]):List[Set[N]] = {
    partialReduce(nodes.map { n => Set(n) }) { case (n1s, n2s) =>
      val lca = leastCommonAncesstor((n1s ++ n2s).map(n => ctrlOf(n))).get
      if (lca.style == ForkJoin) {
        dbg(s"ForkJoin merging $n1s $n2s")
        Some(n1s ++ n2s) }
      else {
        None
      }
    }
  }

}
