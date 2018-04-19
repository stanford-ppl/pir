package prism

abstract class Pass(implicit val compiler:Compiler) extends Logging {

  implicit val pass:this.type = this
  lazy val name = this.getClass.getSimpleName
  override def toString = name
  
  def reset = {}

  def initPass(runner:RunPass[_]):Unit = {
    infor(s"Running ${runner.name} ...")
    tic
  }

  def runPass(runner:RunPass[_]):Unit = runPass
  def runPass:Unit = {}

  def finPass(runner:RunPass[_]):Unit = {
    check
    info(s"Finished ${runner.name} in ${toc("ms")}ms")
  }

  def check(runner:RunPass[_]):Unit = check
  def check:Unit = {}

  def run(runner:RunPass[_]):this.type = {
    initPass(runner)
    runPass(runner)
    finPass(runner)
    this
  }

  def run:this.type = {
    val runner = RunPass[this.type](compiler.session, -1)
    runner.setPass(this)
    run(runner)
  }

  def quote(n:Any):String

}
