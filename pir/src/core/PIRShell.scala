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
  def staging(top:Top) = {}

}

object pload extends PIRShell with Session {
  override def setArgs(args:Array[String]) = {
    var addArgs = ""
    addArgs += s" --load --debug --dot"
    super.setArgs(addArgs.split(" ") ++ args)
  }
  override def loadSession = {
    import config._
    setOption(List(s"--pir-home=${config.cwd}"))
    val appPath = buildPath(dirName(option[String]("ckpt")), "../../")
    setOption(List(s"--path=$appPath"))
    super[Session].loadSession
    if (pirenv._states.isEmpty) {
      err[Unit](s"Load session failed", false)
      sys.exit(-1)
    }
  }
}
  
object psh extends PIRShell with Session {

  override def setArgs(args:Array[String]) = {
    var addArgs = ""
    addArgs += s" --load --debug --dot"
    super.setArgs(addArgs.split(" ") ++ args)
  }
  override def loadSession = {
    import config._
    val appPath = buildPath(dirName(option[String]("ckpt")), "../../")
    setOption(List(s"--path=$appPath"))
    val start = getArgOption[Int]("start-id").flatMap { _.getValue }.getOrElse(-1)
    super[Session].loadSession
    if (pirenv._states.isEmpty) {
      err(s"Load session failed")
    }
    setOption(List(s"--start-id=$start "))
  }

  override def initSession = {
    import config._

    //addPass(enableDot, new PIRCtxDotGen(s"simple.dot"))
    //addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))
    //addPass(enableDot, new PIRIRDotGen(s"top.dot"))
    //addPass(new PIRCtxDotGen(s"simple.dot"))
    addPass(runtimeAnalyzer)
    addPass(tungstenPIRGen)
    //addPass(genPsim, prouteLinkGen)
    //addPass(genPsim, prouteNodeGen)
    //addPass(genPsim, psimConfigGen)
    //addPass(runPsim, psimRunner)
    //addPass(psimParser)
    addPass(new PIRCtxDotGen(s"simple10.dot"))
    addPass(new PIRTopDotGen(s"top10.dot"))

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
