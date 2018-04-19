package prism

case class RunPass[P<:Pass:ClassTag](session:Session, id:Int) extends Serializable with RunPassStatus {
  val pct = implicitly[ClassTag[P]]
  var _pass:Option[P] = None
  def pass:Pass = _pass.get
  var name:String = _
  def logFile = s"$name.log"

  def setPass(pass:Pass) = {
    pass match {
      case pass:P => this._pass = Some(pass)
      case _ => throw SessionRestoreFailure(s"Restored RunPass[${pct}] cannot load $pass") 
    }
    this.name = s"$pass-$id"
    dependencies.clear
  }

  def clearPass = {
    _pass = None
    dependencies.clear
  }

  val dependencies = ListBuffer[RunPass[_]]()
  def dependsOn(deps:Pass*):this.type = {
    deps.foreach { dep =>
      dependencies += session.passes(dep).last //TODO: If dependency is not added, session.passes does not contain dep yet.
    }
    this
  }
  def unfinishedDependencies = dependencies.filter { !_.succeeded }
  def isDependencyFree = unfinishedDependencies.isEmpty

  def prevRuns:Iterable[RunPass[_]] = {
    session.runPasses.slice(0, id)
  }

  def run(implicit compiler:Compiler):Unit = {
    if (!shouldRun) return
    if (!isDependencyFree) 
      err(s"Cannot run pass $name due to dependencies=${unfinishedDependencies.map(_.name).mkString(",")} haven't run")

    Try {
      withLog(append=false) {
        pass.run(this)
      }
    } match {
      case Success(_) => setSucceed
      case Failure(e:Throwable) => setFailed; throw e
    }
  }

  def withLog(append:Boolean)(lambda: => Unit)(implicit compiler:Compiler) {
    if (pass.logger.isOpen) lambda 
    else pass.logger.withOpen(logFile, append) { lambda }
  }
}

trait RunPassStatus {
  private var initStatus:RunStatus = _
  private var status:RunStatus = _

  def rerun:this.type = { status = initStatus; this }

  def init = status = initStatus
  def initDisabled = initStatus = RunDisabled
  def initPending = initStatus = RunPending
  def setSucceed = status = RunSucceeded
  def setFailed = status = RunFailed

  def shouldRun = status match {
    case RunPending => true
    case _ => false
  }

  def hasRun = status match {
    case RunSucceeded => true
    case RunFailed => true
    case _ => false
  }

  def failed = status match {
    case RunFailed => true
    case _ => false
  }

  def succeeded = status match {
    case RunSucceeded => true
    case _ => false
  }

}

sealed trait RunStatus extends Serializable
case object RunDisabled extends RunStatus
case object RunPending extends RunStatus
case object RunSucceeded extends RunStatus
case object RunFailed extends RunStatus

case class SessionRestoreFailure(msg:String) extends PIRException
