package pir

import prism.graph._
import pir.node._
import pir.pass._
import pir.codegen._
import pir.mapper._
import prism.codegen._
import spade.codegen._
import prism._

trait PIRShell extends PIRApp with Logging with Session {
  def staging(top:Top):Unit = {}

  override def loadSession = {
    import config._
    var args = ""
    args += s" --load --debug -- dot --out=${dirName(option[String]("ckpt"))} --trace=false"
    args += " --run-psim --psim"
    args += s" --psim-out=${cwd}/../gen/shell/"
    args += s" --psim-home=${cwd}/../plastisim/"
    args += s" --proute-home=${cwd}/../plastiroute/"
    //args += " --net=asic "
    args += " --pattern=checkerboard --row=11 --col=11 "
    getArgOption("proute-opts").get.updateValue("-i1000 -p100 -t1 -d100 -S5")
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
}

object pload extends PIRShell
  
object psh extends PIRShell {

  override def initSession = {
    import config._

    //addPass(enableDot, new PIRCtxDotGen(s"simple.dot"))
    //addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))
    //addPass(enableDot, new PIRIRDotGen(s"top.dot"))
    //addPass(new PIRCtxDotGen(s"simple.dot"))
    addPass(tungstenPIRGen)
    //addPass(genPsim, prouteLinkGen)
    //addPass(genPsim, prouteNodeGen)
    //addPass(genPsim, psimConfigGen)
    //addPass(runPsim, psimRunner)
    //addPass(psimParser)
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
