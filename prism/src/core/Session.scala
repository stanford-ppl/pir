package prism

import scala.collection.mutable

trait Session { self:Compiler =>
  var restore = false

  val runners = mutable.ListBuffer[Runner]()
  val storedRunneres = mutable.ListBuffer[Runner]()

  val passes = mutable.Map[Pass, mutable.ListBuffer[Runner]]()

  var currInit = 0

  def addPass[P<:Pass:ClassTag](pass:P):Runner = addPass(true, pass)
  def addPass[P<:Pass:ClassTag](shouldRun:Boolean, pass:P):Runner = {
    passes.getOrElseUpdate(pass, mutable.ListBuffer[Runner]())
    val runner = Runner(this, currInit)
    if (shouldRun) runner.initPending else runner.initDisabled
    runners += runner
    runner.setPass(pass)
    passes(pass) += runner
    currInit += 1
    runner
  }
  def addPass(name:String)(lambda: Runner => Unit):Runner = addPass(QuickPass(name, lambda))
  case class QuickPass(override val name:String, lambda: Runner => Unit) extends Pass {
    override def runPass = lambda(runner)
  }

  implicit class RunnerOp(runner:Runner) {
    def ==> (next:Runner) = {
      next.dependsOn(runner)
      next
    }
  }

  def loadSession = {
    val opt = config.getArgOption[Int]("start-id").get 
    if (config.load)  {
      try {
        val (startId, design) = loadFromFile[(Int, Serializable)](config.checkPointPath)
        loadDesign(design)
        if (opt.getValue.isEmpty) opt.updateValue(startId+1)
      } catch {
        case e@(_:SessionRestoreFailure | _:java.io.InvalidClassException | _:java.io.FileNotFoundException | _:ClassCastException) =>
          warn(s"Restore design failed: ${e}")
          opt.updateValue(0)
        case e:Throwable => throw e
      }
    }
    if (opt.getValue.isEmpty) opt.updateValue(0)
  }

  def initSession:Unit = {}

  def loadDesign(loaded:Any):Unit = {}
  def getDesign:Serializable = null
  def saveSession(path:String, force:Boolean=false) = addPass("SaveSession"){runner =>
    if (config.save | force) {
      val saved = (runner.id, compiler.getDesign)
      saveToFile(saved, path)
      //loadFromFile[(Int, Serializable)](path)
    }
  }

  var currRun:Runner = _
  def runSession:Boolean = {
    passes.foreach { case (pass, _) => pass.reset }
    val error = runners.foldLeft(false) { 
      case (false,runner) =>
        if (runner.id < config.startRunId) runner.setSucceed
        if (runner.id >= config.startRunId && config.endRunId.fold(true) { runner.id < _ }) {
          currRun = runner
          runner.run
        }
        runner.compileErr
      case (true,runner) => true
    }
    if (error) return false
    val failed = runners.filter { _.failed }
    if (failed.nonEmpty) {
      err(s"Failed passes:", false)
      failed.foreach { r => err(r.name, false)}
    }
    failed.isEmpty
  }

  def hasRun(pass:Pass):Boolean = passes(pass).exists(_.hasRun)

  def hasRunAll(pass:Pass):Boolean = passes(pass).exists(_.hasRun) 

  def runCount(pass:Pass) = passes(pass).filter(_.hasRun).size

  def hasRun[P<:Pass:ClassTag] = runnersOf[P].exists(_.hasRun)

  def hasRunAll[P<:Pass:ClassTag] = runnersOf[P].forall(_.hasRun)
  
  def runnersOf[P<:Pass:ClassTag] = {
    passes.flatMap {
      case (pass:P, runners) => runners
      case (pass, runners) => Nil
    }
  }

  def getCurrentRunner(pass:Pass) = {
    val runner = if (!passes.contains(pass)) None else passes(pass).filter { _.isRunning }.headOption
    runner.getOrElse {
      val runner = Runner(this, 999)
      runner.setPass(pass)
      runner.initPending
      runner
    }
  }

}

