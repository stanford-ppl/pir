package pir
package util

object PIRConfigSetter extends PIR with Printer {

  override def main(args: Array[String]): Unit = {
    setArgs(args)
    withOpen(configPath, false) {
      config.optionMap.foreach { case (key, aopt) =>
        aopt.value.foreach { v =>
          info(s"Set $key=$v")
          emitln(s"$key=$v")
        }
      }
    }
  }
  
}

