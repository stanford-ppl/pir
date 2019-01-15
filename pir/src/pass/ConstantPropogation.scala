package pir
package pass

import pir.node._
import prism.graph._
import prism.graph.implicits._

import scala.collection.mutable

// BFS is slightly faster than DFS
class ConstantPropogation(implicit compiler:PIR) extends PIRTraversal with Transformer with BFSBottomUpTopologicalTraversal with UnitTraversal {

  val forward = true

  val WrittenByConstData = MatchRule[MemRead, (MemRead, Const)] { read =>
    val ConstData = MatchRule[MemWrite, Const] { write =>
      write.data.T match {
        case c@Const(_) => Some(c)
        case _ => None
      }
    }
    read.mem.T.inAccesses match {
      case List(ConstData(c)) => Some((read, c))
      case _ => None
    }
  }

  //val EvalOp = MatchRule[OpDef, ] { op =>
    //val in = op.input.T.map { 
      //case Const(c) => c
      //case in => in
    //}
  //}

  override def visitNode(n:N):T = /*dbgblk(s"visitNode:${quote(n)}") */{
    n.to[PIRNode].foreach { n =>
      n.localIns.filter { _.as[Field[_]].name == "en" }.foreach { en =>
        en.connected.distinct.foreach {
          case out@OutputField(Const(true), "out") => 
            en.disconnectFrom(out)
            dbg(s"${en.src}.${en} disconnect ${out.src}.${out}")
          case _ => 
        }
      }
    }
    n match {
      case n:Shuffle if n.from.T == n.to.T =>
        val base = assertOne(n.base.connected, s"$n.base.connected").as[Output]
        swapOutput(n.out, base)
      case WrittenByConstData(read:MemRead, c:Const) =>
        swapOutput(read.out, c.out)
      case n => 
    }
    super.visitNode(n)

  }

}
