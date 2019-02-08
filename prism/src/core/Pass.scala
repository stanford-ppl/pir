package prism
import prism.graph._

abstract class Pass(implicit val compiler:Compiler) extends Logging {

  protected implicit val pass:this.type = this
  def name = this.getClass.getSimpleName
  override def toString = name
  
  def reset = {}

  def runner = compiler.getCurrentRunner(this)
  def config = compiler.config
  override def debug = config.debug

  def initPass:Unit = {
    this match {
      case self:Traversal => self.resetTraversal
      case _ =>
    }
  }

  def runPass:Unit = {
    this match {
      case self:HierarchicalTraversal => self.traverseTop
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

  def handle(e:Throwable) = {
    dbgblk(s"$e") {
      e.getStackTrace.foreach(dbg)
    }
    logger.closeAllBuffersAndWrite
  }

}
