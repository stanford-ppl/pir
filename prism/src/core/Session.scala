package prism

import scala.collection.mutable

@SerialVersionUID(123L)
class Session extends Serializable {
  var restore = false

  val runners = mutable.ListBuffer[Runner[_]]()
  val storedRunneres = mutable.ListBuffer[Runner[_]]()

  val passes = mutable.Map[Pass, mutable.ListBuffer[Runner[_]]]()

  var rerunning = false

  var savingCheckPoint = -1
  def rerun(block: => Unit)(implicit compiler:Compiler):Unit = {
    val saved = rerunning
    rerunning = true
    savingCheckPoint = currInit
    block
    rerunning = saved
  }

  var currInit = 0

  def addPass[P<:Pass:ClassTag](pass:P):Runner[_] = addPass(true, pass)
  def addPass[P<:Pass:ClassTag](shouldRun:Boolean, pass:P):Runner[_] = {
    passes.getOrElseUpdate(pass, mutable.ListBuffer[Runner[_]]())
    val runner = if (restore && !rerunning) {
      storedRunneres(currInit)
    } else {
      val runner = Runner[P](this, currInit)
      if (shouldRun) runner.initPending else runner.initDisabled
      runner
    }
    runners += runner
    runner.setPass(pass)
    passes(pass) += runner
    currInit += 1
    runner
  }

  def saveSession(path:String) = {
    restore = true
    currInit = 0
    savingCheckPoint = -1
    currRun = null
    runners.foreach { _.clearPass }
    storedRunneres.clear
    storedRunneres ++= runners
    runners.clear
    passes.clear
    saveToFile(this, path)
  }

  var currRun:Runner[_] = _
  def run(implicit compiler:Compiler):Boolean = {
    passes.foreach { case (pass, _) => pass.reset }
    runners.foreach { runner =>
      if (compiler.save && runner.id==savingCheckPoint) compiler.saveDesign
      currRun = runner
      runner.run
    }
    val succeed = runners.forall { !_.failed }
    if (compiler.save) {
      if (savingCheckPoint == -1) {
        compiler.saveDesign
        compiler.loadDesign
      }
      saveSession(compiler.sessionPath)
    }
    succeed
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
