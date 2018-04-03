package prism

import scala.collection.mutable

@SerialVersionUID(123L)
class Session extends Serializable {
  var restore = false

  val runPasses = mutable.ListBuffer[RunPass[_]]()

  val passes = mutable.Map[Pass, mutable.ListBuffer[RunPass[_]]]()

  def addPass[P<:Pass:ClassTag](pass:P):RunPass[_] = {
    passes.getOrElseUpdate(pass, mutable.ListBuffer[RunPass[_]]())
    val runPass = newRun(pass)
    passes(pass) += runPass 
    runPasses += runPass
    runPass
  }

  var currInit = 0
  def newRun[P<:Pass:ClassTag](pass:P) = {
    val run = if (restore) runPasses(currInit) else RunPass[P](this, currInit)
    run.setPass(pass)
    currInit += 1
    run
  }

  def saveSession(path:String) = {
    restore = true
    currInit = 0
    currRun = 0
    runPasses.foreach { _.clearPass }
    passes.clear
    saveToFile(this, path)
  }

  var currRun = 0
  def run(implicit compiler:Compiler) = {
    passes.foreach { case (pass, _) => pass.reset }
    runPasses.foreach { rp =>
      currRun = rp.id
      rp.run
    }
  }

  def hasRun(pass:Pass):Boolean = passes(pass).exists(_.hasRun)

  def hasRunAll(pass:Pass):Boolean = passes(pass).exists(_.hasRun) 

  def runCount(pass:Pass) = passes(pass).filter(_.hasRun).size

  def hasRun[P<:Pass:ClassTag] = runsOf[P].exists(_.hasRun)

  def hasRunAll[P<:Pass:ClassTag] = runsOf[P].forall(_.hasRun)
  
  def runsOf[P<:Pass:ClassTag] = {
    passes.flatMap {
      case (pass:P, runPasses) => runPasses
      case (pass, runPasses) => Nil
    }
  }
}
