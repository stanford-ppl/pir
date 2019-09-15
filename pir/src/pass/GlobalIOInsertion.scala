package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

trait GlobalIOInsertion extends PIRTransformer {

  def insertGlobalInput(global:GlobalContainer):Unit = {
    within(global) {
      global.depsFrom.foreach { case (out, ins) => insertGlobalInput(global, out, ins) }
    }
  }

  def insertGlobalInput(
    global:GlobalContainer,
    out:Output[PIRNode], 
    inputs:Seq[Input[PIRNode]]
  ):Unit = {
    val ins = inputs.filterNot { _.src.isInstanceOf[GlobalInput] }
    if (ins.isEmpty) return
    dbgblk(s"insertGlobalInput($global, ${dquote(out)}, ${dquote(inputs)})"){
      out.src match {
        case dep:GlobalInput if dep.isDescendentOf(global) => dep
        case dep:GlobalInput => 
          val gout = dep.in.T.out
          ins.foreach { in => swapConnection(in, out, gout) }
          insertGlobalInput(global, gout, ins)
        case dep =>
          val gin = within(global) { 
            allocate[GlobalInput] { _.in.isConnectedTo(out) } { stage(GlobalInput().in(out)) } 
          }
          ins.foreach { in => swapConnection(in, out, gin.out) }
          gin
      }
    }
  }

  def insertGlobalOutput(global:GlobalContainer):Unit = {
    within(global) {
      global.depedsTo.foreach { case (out, ins) => 
        insertGlobalOutput(global, out, ins)
      }
    }
  }

  def insertGlobalOutput(
    global:GlobalContainer,
    out:Output[PIRNode], 
    ins:Seq[Input[PIRNode]]
  ):GlobalOutput = dbgblk(s"insertGlobalOutput($global, ${dquote(out)}, ${dquote(ins)})"){
    out.src match {
      case depedFrom:GlobalOutput if depedFrom.isDescendentOf(global) => depedFrom
      case depedFrom:GlobalOutput => bug(s"impossible case insertGlobalOutput")
      case depedFrom =>
        val gout = within(global) { 
          allocate[GlobalOutput]{ _.in.isConnectedTo(out) } { stage(GlobalOutput().in(out)) }
        }
        ins.foreach { in => swapConnection(in, out, gout.out) }
        gout
    }
  }

  def insertGlobalIO(global:GlobalContainer) = {
    insertGlobalInput(global)
    insertGlobalOutput(global)
  }

}

