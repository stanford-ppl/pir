package prism

abstract class Pass(implicit val compiler:Compiler) extends Logging {

  protected implicit val pass:this.type = this
  lazy val name = this.getClass.getSimpleName
  override def toString = name
  
  def reset = {}

  def runner = compiler.session.getCurrentRunner(this)

  def initPass:Unit = {}

  def runPass:Unit = {}

  def finPass:Unit = {}

  def run:this.type = {
    initPass
    runPass
    finPass
    this
  }

  def prologue(name:String) = {
    infor(s"Running ${name} ...")
  }

  def epilogue(name:String, time:String) = {
    info(s"Finished ${name} in ${time}")
  }

  def succeed:Unit = {
    info(Console.GREEN, "success", s"$name succeeded")
    runner.setSucceed
  }

  def fail(f:Any):Unit = {
    info(Console.RED, "fail", s"$name failed: $f")
    runner.setFailed
  }

}
