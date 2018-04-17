package prism.util

object ConsoleLogger extends Logging {
  override def toString = "ConsoleLogger"
  logger.openStdout
}
