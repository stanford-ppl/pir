package prism
import prism.graph._

abstract class Pass(implicit val compiler:Compiler) extends Logging with GraphTraversal {

  protected implicit val pass:this.type = this
  def name = this.getClass.getSimpleName
  override def toString = name
  
  def reset = {}

  def runner = compiler.session.getCurrentRunner(this)

  def initPass:Unit = {}

  def runPass:Unit = {
    this match {
      case self:TopDownTraversal => self.traverseTop
      case _ =>
    }
  }

  def finPass:Unit = {}

  def run:this.type = {
    initPass
    runPass
    finPass
    this
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
