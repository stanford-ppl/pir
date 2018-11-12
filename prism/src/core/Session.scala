package prism

import scala.collection.mutable

trait Session { self:Compiler =>
  var restore = false

  val runners = mutable.ListBuffer[Runner[_]]()
  val storedRunneres = mutable.ListBuffer[Runner[_]]()

  val passes = mutable.Map[Pass, mutable.ListBuffer[Runner[_]]]()

  var currInit = 0

  def addPass[P<:Pass:ClassTag](pass:P):Runner[_] = addPass(true, pass)
  def addPass[P<:Pass:ClassTag](shouldRun:Boolean, pass:P):Runner[_] = {
    passes.getOrElseUpdate(pass, mutable.ListBuffer[Runner[_]]())
    val runner = Runner[P](this, currInit)
    if (shouldRun) runner.initPending else runner.initDisabled
    runners += runner
    runner.setPass(pass)
    passes(pass) += runner
    currInit += 1
    runner
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
  def saveSession = addPass(new SaveSession())

  var currRun:Runner[_] = _
  def runSession:Boolean = {
    passes.foreach { case (pass, _) => pass.reset }
    runners.foreach { runner =>
      if (runner.id >= config.startRunId) {
        currRun = runner
        runner.run
      }
    }
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
      val runner = Runner[pass.type](this, 999)
      runner.setPass(pass)
      runner.initPending
      runner
    }
  }
}

class SaveSession(implicit compiler:Compiler) extends Pass {
  override def runPass = {
    if (config.save) {
      val saved = (runner.id, compiler.getDesign)
      saveToFile(saved, config.checkPointPath)
      loadFromFile[(Int, Serializable)](config.checkPointPath)
    }
  }
}
