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

  var currRun:Runner[_] = _
  def runSession:Boolean = {
    passes.foreach { case (pass, _) => pass.reset }
    runners.foreach { runner =>
      if (runner.id >= config.startRunId) {
        currRun = runner
        runner.run
      }
    }
    runners.forall { !_.failed }
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
