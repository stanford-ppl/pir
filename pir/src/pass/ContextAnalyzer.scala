package pir
package pass

import pir.node._
import prism.graph._

class ContextAnalyzer(implicit compiler:PIR) extends BufferAnalyzer {

  override def runPass = {
    pirTop.collectDown[Context]().foreach(analyzeCtx)
  }

  def analyzeCtx(ctx:Context) = dbgblk(s"analyzeCtx($ctx)"){
    //analyzePipeReg(ctx)
    tokenInInsertion(ctx)
  }

  //def analyzePipeReg(ctx:Context):Unit = {
    //ctx.collectDown[BufferRead]().foreach { mem =>
      //val writer = mem.in.T
      //if (mem.out.T.contains(writer.data.T)) {
        //dbg(s"$mem has single cycle accumulate")
        //mem.isPipeReg := true
      //}
    //}
  //}

  def tokenInInsertion(ctx:Context):Unit = {
    if (ctx.collectUp[ArgFringe]().nonEmpty) return
    if (ctx.collectDown[StreamCommand]().nonEmpty) return
    val nonLutInputs = ctx.deps(filter=Some({ case (OutputField(n:LUT, _), _) => false; case _ => true }))
    if (nonLutInputs.isEmpty) {
      val hostInCtx = pirTop.argFringe.collectDown[HostInController]().head.ctx.get
      dbg(s"$ctx doesn't have any non lut inputs.")
      insertToken(hostInCtx, ctx)
    }
  }

}
