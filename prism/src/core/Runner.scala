package prism

case class Runner(session:Session, id:Int) extends Serializable with RunnerStatus {
  var _pass:Option[Pass] = None
  def pass:Pass = _pass.get
  var name:String = _
  def logFile = s"$name.log"
  def startRunId:Int = session.asInstanceOf[Compiler].config.startRunId

  def setPass(pass:Pass) = {
    this._pass = Some(pass)
    this.name = s"${"%02d".format(id)}-$pass"
    dependencies.clear
    this
  }

  def clearPass = {
    _pass = None
    dependencies.clear
  }

  val dependencies = ListBuffer[Runner]()
  def dependsOn(dep:Runner):this.type = {
    dependencies += dep
    this
  }
  def dependsOn(deps:Pass*):this.type = {
    val runners = deps.map { dep =>
      val runners = session.passes(dep).filterNot { _ == this }
      runners.last
    }
    runners.foreach { dependsOn }
    this
  }

  def prevRuns:Iterable[Runner] = {
    session.runners.slice(0, id)
  }

  def run(implicit compiler:Compiler):Unit = {
    if (!shouldRun) return
    if (hasRun) return
      dependencies.foreach { dependency =>
      if (/*dependency.shouldRun && */!dependency.succeeded) {
        //warn(s"$name not run due to dependency ${dependency.pass} not success")
        return
      }
    }

    setRunning

    pass.withLog(compiler.outDir, logFile, append=false) {
      tic(s"Running ${cstr(Console.CYAN, name)} ...")
      Try(pass.run) match {
        case Success(_) if isRunning => setSucceed
        case Success(_) => 
        case Failure(e:Throwable) => 
          setFailed
          err(s"$name throw $e", exception=false)
          pass.handle(e)
      }
      info(s"Finished ${cstr(Console.CYAN, name)} in ${toc("ms")}ms")
    }
  }

}

trait RunnerStatus {
  protected var initStatus:Status = _
  var status:Status = _
  val id:Int

  def rerun:this.type = { status = initStatus; this }
  def startRunId:Int

  def init = status = initStatus
  def initDisabled = { initStatus = Disabled; init }
  def initPending = { initStatus = Pending; init }
  def setSucceed = status = Succeeded
  def setFailed = status = Failed
  def setRunning = status = Running

  def shouldRun = status match {
    case status if id < startRunId => false
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
