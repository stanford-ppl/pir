package pir

import prism.graph._
import pir.node._
import pir.pass._
import pir.codegen._
import pir.mapper._
import prism.codegen._
import spade.codegen._
import prism._

object PIRShell extends PIRApp with Logging with Session {
  
  //lazy val ctxMerging = new ContextMerging()
  //lazy val initializer = new TargetInitializer()
  //lazy val psimRunner = new PlastisimRunner()
  //lazy val psimConfigGen = new PlastisimConfigGen()
  //lazy val psimParser = new PlastisimLogParser()
  //lazy val placerAndRouter = new PlaceAndRoute()
  //lazy val prouteLinkGen = new PlastirouteLinkGen()
  //lazy val prouteNodeGen = new PlastirouteNodeGen()

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
      err(s"Load session failed")
    }
    setAnnotation(pirTop)
    args = s"--start-id=$start "
    setOption(args.split(" ").map(_.trim).toList)
  }

  //override def initSession = {
    //import config._

    //addPass(initializer)
    //addPass(enableDot, new PIRCtxDotGen(s"simple.dot"))

    // ------- Analysis and Transformations --------
    //addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))
    //addPass(enableDot, new PIRIRDotGen(s"top.dot"))
    //addPass(new PIRCtxDotGen(s"simple.dot"))
    //addPass(genPsim, ctxMerging)

    //addPass(initializer)
    //addPass(enableMapping, placerAndRouter)

    //// ------- Codegen  --------
    //addPass(tungstenPIRGen)
    //addPass(genPsim, prouteLinkGen)
    //addPass(genPsim, prouteNodeGen)
    //addPass(genPsim, psimConfigGen)
    //addPass(runPsim, psimRunner)
    //addPass(psimParser)
    //addPass(new PIRCtxDotGen(s"simple10.dot"))
    //addPass(new PIRIRDotGen(s"top10.dot"))
    //addPass(enableDot, new PIRIRDotGen(s"top.dot"))

    //addPass(areaPowerStat).dependsOn(psimConfigCodegen, cuPlacer)
    //addPass("tracer") { runner =>
      //val ctxs = pirTop.collectDown[Context]()
      //ctxs.filter(isStarved).filterNot { ctx =>
        //visitIn(ctx).exists(isStarved)
      //}.foreach { ctx =>
        //println(ctx, visitIn(ctx))
      //}
    //}
  //}

  def isStarved(ctx:Context) = ctx.state.get == "STARVE" || ctx.state.get == "BOTH"

  def visitIn(n:PIRNode):List[Context] = visitGlobalIn(n).flatMap {
    case x:GlobalIO => visitIn(x)
    case x:Context => List(x)
    case x:Memory => Nil
    case x => x.collectUp[Context]()
  }.distinct

  def visitOut(n:PIRNode):List[Context] = visitGlobalOut(n).flatMap {
    case x:GlobalIO => visitOut(x)
    case x:Context => List(x)
    case x:Memory => Nil
    case x => x.collectUp[Context]()
  }.distinct

}
