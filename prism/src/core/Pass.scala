package prism

abstract class Pass(implicit val compiler:Compiler) extends Logging {

  implicit val pass:this.type = this
  lazy val name = this.getClass.getSimpleName
  override def toString = name
  
  def reset = {}

  def runner = compiler.session.getCurrentRunner(this)

  def initPass:Unit = {
    infor(s"Running ${runner.name} ...")
    tic
  }

  def runPass:Unit = {}

  def finPass:Unit = {
    info(s"Finished ${runner.name} in ${toc("ms")}ms")
  }

  def run:this.type = {
    initPass
    runPass
    finPass
    this
  }

  def quote(n:Any):String

}
