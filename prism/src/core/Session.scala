package prism

import scala.collection.mutable

@SerialVersionUID(123L)
class Session extends Serializable {
  var restore = false

  val runners = mutable.ListBuffer[Runner[_]]()
  val storedRunneres = mutable.ListBuffer[Runner[_]]()

  val passes = mutable.Map[Pass, mutable.ListBuffer[Runner[_]]]()

  var rerunning = false
  def rerun(block: => Unit):Unit = {
    val saved = rerunning
    rerunning = true
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
    currRun = null
    runners.foreach { _.clearPass }
    storedRunneres.clear
    storedRunneres ++= runners
    runners.clear
    passes.clear
    saveToFile(this, path)
  }

  var currRun:Runner[_] = _
  def run(implicit compiler:Compiler) = {
    passes.foreach { case (pass, _) => pass.reset }
    runners.foreach { runner =>
      currRun = runner
      runner.run
    }
  }

  def hasRun(pass:Pass):Boolean = passes(pass).exists(_.hasRun)

  def hasRunAll(pass:Pass):Boolean = passes(pass).exists(_.hasRun) 

  def runCount(pass:Pass) = passes(pass).filter(_.hasRun).size

  def hasRun[P<:Pass:ClassTag] = runsOf[P].exists(_.hasRun)

  def hasRunAll[P<:Pass:ClassTag] = runsOf[P].forall(_.hasRun)
  
  def runsOf[P<:Pass:ClassTag] = {
    passes.flatMap {
      case (pass:P, runners) => runners
      case (pass, runners) => Nil
    }
  }
}
