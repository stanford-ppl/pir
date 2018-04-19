package prism

case class Runner[P<:Pass:ClassTag](session:Session, id:Int) extends Serializable with RunnerStatus {
  val pct = implicitly[ClassTag[P]]
  var _pass:Option[P] = None
  def pass:Pass = _pass.get
  var name:String = _
  def logFile = s"$name.log"

  def setPass(pass:Pass) = {
    pass match {
      case pass:P => this._pass = Some(pass)
      case _ => throw SessionRestoreFailure(s"Restored Runner[${pct}] cannot load $pass") 
    }
    this.name = s"$pass-$id"
    dependencies.clear
    this
  }

  def clearPass = {
    _pass = None
    dependencies.clear
  }

  val dependencies = ListBuffer[Runner[_]]()
  def dependsOn(deps:Pass*):this.type = {
    deps.foreach { dep =>
      dependencies += session.passes(dep).last //TODO: If dependency is not added, session.passes does not contain dep yet.
    }
    this
  }
  def unfinishedDependencies = dependencies.filter { !_.succeeded }
  def isDependencyFree = unfinishedDependencies.isEmpty

  def prevRuns:Iterable[Runner[_]] = {
    session.runners.slice(0, id)
  }

  def run(implicit compiler:Compiler):Unit = {
    if (!shouldRun) return
    if (hasRun) return
    dependencies.foreach { dependency =>
      if (!dependency.shouldRun) return
      if (!dependency.succeeded) {
        warn(s"Cannot run pass $name due to ${unfinishedDependencies.map(_.name).mkString(",")} not succeed")
        return
      }
    }

    Try {
      pass.withLog(compiler.outDir, logFile, append=false) { pass.run }
    } match {
      case Success(_) if isPending => setSucceed
      case Success(_) => 
      case Failure(e:Throwable) => setFailed; throw e
    }
  }

}

trait RunnerStatus {
  private var initStatus:RunStatus = _
  private var status:RunStatus = _

  def rerun:this.type = { status = initStatus; this }

  def init = status = initStatus
  def initDisabled = { initStatus = RunDisabled; init }
  def initPending = { initStatus = RunPending; init }
  def setSucceed = status = RunSucceeded
  def setFailed = status = RunFailed

  def shouldRun = status match {
    case RunPending => true
    case RunSucceeded => true
    case RunFailed => true
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

  def isPending = status == RunPending

}

sealed trait RunStatus extends Serializable
case object RunDisabled extends RunStatus
case object RunPending extends RunStatus
case object RunSucceeded extends RunStatus
case object RunFailed extends RunStatus

case class SessionRestoreFailure(msg:String) extends PIRException
