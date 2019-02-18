package pir

import prism.graph._
import pir.node._
import pir.pass._
import pir.codegen._
import pir.mapper._
import prism.codegen._
import spade.codegen._
import prism._

trait PIRShell extends PIRApp with Logging {
  def staging(top:Top):Unit = {}

}

object pload extends PIRShell with Session {
  override def loadSession = {
    import config._
    var args = ""
    args += s" --load --debug --dot --out=${dirName(option[String]("ckpt"))}"
    setOption(args.split(" ").map(_.trim).toList)
    super[Session].loadSession
    if (_states.isEmpty) {
      err(s"Load session failed", false)
      sys.exit(0)
    }
    setAnnotation(pirTop)
  }
}
  
object psh extends PIRShell with Session {

  override def loadSession = {
    import config._
    var args = ""
    args += s" --load --debug --dot --out=${dirName(option[String]("ckpt"))}"
    setOption(args.split(" ").map(_.trim).toList)
    val start = getArgOption[Int]("start-id").flatMap { _.getValue }.getOrElse(-1)
    super[Session].loadSession
    if (_states.isEmpty) {
      err(s"Load session failed", false)
      sys.exit(0)
    }
    setAnnotation(pirTop)
    args = s"--start-id=$start "
    setOption(args.split(" ").map(_.trim).toList)
  }

  override def initSession = {
    import config._

    //addPass(enableDot, new PIRCtxDotGen(s"simple.dot"))
    //addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))
    //addPass(enableDot, new PIRIRDotGen(s"top.dot"))
    //addPass(new PIRCtxDotGen(s"simple.dot"))
    //addPass(tungstenPIRGen)
    //addPass(genPsim, prouteLinkGen)
    //addPass(genPsim, prouteNodeGen)
    //addPass(genPsim, psimConfigGen)
    addPass(runPsim, psimRunner)
    addPass(psimParser)
    addPass(new PIRCtxDotGen(s"simple10.dot"))
    addPass(new PIRIRDotGen(s"top10.dot"))

    //addPass("tracer") { runner =>
      //val ctxs = pirTop.collectDown[Context]()
      //ctxs.filter(isStarved).filterNot { ctx =>
        //visitIn(ctx).exists(isStarved)
      //}.foreach { ctx =>
        //println(ctx, visitIn(ctx))
      //}
    //}
  }

}
