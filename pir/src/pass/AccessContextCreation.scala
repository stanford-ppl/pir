package pir
package pass

import pir.node._
import prism.graph._
import spade.param._
import scala.collection.mutable

class AccessContextCreation(implicit compiler:PIR) extends PIRPass with Transformer with BufferAnalyzer with DependencyAnalyzer {

  override def runPass = {
    pirTop.collectDown[MemoryContainer]().foreach { global => global.collectDown[Context]().foreach(split) }
  }

  //override def escape(dep:PIRNode, depedIn:Input[PIRNode], depedCtx:Context) = dbgblk(s"escape($depedIn)") {
    //(dep, depedIn) match {
      //case (_, InputField(deped:Access, "mem")) => false
      //case (_, InputField(deped:LocalOutAccess, "in")) => false
      //case (dep, _) if dep.isDescendentOf(depedCtx) => false

      //case (dep:Const, _) => false // duplicate later

      //case (dep, _) => true
    //}
  //}


  def split(ctx:Context):Unit = {
    if (ctx.collectDown[OpNode]().isEmpty) return
    dbgblk(s"split($ctx)") {
      val k = ctx.global.get
      val newCtx = within(k, ctx.ctrl.get) { Context() }
      dbg(s"newCtx=$newCtx")
      var localNnodes:List[PIRNode] = ctx.collectDown[Access]() 
      localNnodes ++= localNnodes.flatMap { _.accum(visitFunc=visitLocalOut _) }.distinct
      localNnodes ++= ctx.collectDown[TokenWrite]() 
      localNnodes ++= ctx.collectDown[TokenRead]()
      localNnodes.foreach { local => swapParent(local, newCtx) }
      bufferInput(newCtx)
      dupDeps(newCtx, ctx)
      //breakPoint(s"split $ctx")
    }
  }

}
