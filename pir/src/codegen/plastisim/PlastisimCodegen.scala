package pir
package codegen

import pir.node._
import pir.pass._
import prism.util._

trait PlastisimCodegen extends PIRCodegen with FileManager with PlastisimUtil {
  import pirmeta._
  import spademeta._
  import PIRConfig._

  lazy val PLASTISIM_HOME = sys.env.get("PLASTISIM_HOME")
  lazy val PLASTIROUTE_HOME = sys.env.get("PLASTIROUTE_HOME")

  lazy val relativePath = s"configs/gen/${compiler}/${fileName}" 

  lazy val psimOut = getOption[String]("psim_out").orElse {
    sys.env.get("PLASTISIM_HOME").map { home =>
      s"$home/configs/gen/${compiler}"
    }
  }

  def copyGeneratedFile = {
    psimOut.fold {
      warn(s"Specify PLASTISIM_HOME environmental variable to generate to PLASTISIM_HOME/${relativePath}")
    } { psimOut =>
      val genPath = s"$psimOut/$fileName"
      mkdir(dirName(genPath))
      copyFile(outputPath, genPath)
    }
  }

  override def finPass = {
    super.finPass
    copyGeneratedFile
  }

}
