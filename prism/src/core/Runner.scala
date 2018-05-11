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
    this.name = s"$id-$pass"
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
        warn(s"$name not run due to depended ${unfinishedDependencies.map(_.name).mkString(",")} not success")
        return
      }
    }

    setRunning

    pass.withLog(compiler.outDir, logFile, append=false) {
      Try(pass.run) match {
        case Success(_) if isRunning => setSucceed
        case Success(_) => 
        case Failure(e:Throwable) => 
          setFailed
          err(s"$name throw $e", exception=false)
          pass.dbgblk(s"$e") {
            e.getStackTrace.foreach(pass.dbg)
          }
          pass.logger.closeAllBufferAndWrite
      }
    }
  }

}

trait RunnerStatus {
  protected var initStatus:Status = _
  protected var status:Status = _

  def rerun:this.type = { status = initStatus; this }

  def init = status = initStatus
  def initDisabled = { initStatus = Disabled; init }
  def initPending = { initStatus = Pending; init }
  def setSucceed = status = Succeeded
  def setFailed = status = Failed
  def setRunning = status = Running

  def shouldRun = status match {
    case Pending => true
    case Succeeded => true
    case Failed => true
    case _ => false
  }

  def hasRun = status match {
    case Succeeded => true
    case Failed => true
    case _ => false
  }

  def failed = status match {
    case Failed => true
    case _ => false
  }

  def succeeded = status match {
    case Succeeded => true
    case _ => false
  }

  def isPending = status == Pending

  def isRunning = status == Running

  sealed trait Status extends Serializable
  case object Disabled extends Status
  case object Running extends Status
  case object Pending extends Status
  case object Succeeded extends Status
  case object Failed extends Status
}

case class SessionRestoreFailure(msg:String) extends PIRException
