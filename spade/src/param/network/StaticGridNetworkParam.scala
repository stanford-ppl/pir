package spade
package param

import prism.node._
import prism.collection.mutable.Table

import SpadeConfig._
abstract class StaticGridNetworkParam[B<:PinType:ClassTag] extends NetworkParam[B] {
  lazy val meshTopParam = collectOut[GridTopParam]().head
  lazy val numRows:Int = meshTopParam.numRows
  lazy val numCols:Int = meshTopParam.numCols
  lazy val argFringeParam = meshTopParam.fringePattern.argFringeParam
  lazy val numArgIns:Int = argFringeParam.numArgIns
  lazy val numArgOuts:Int = argFringeParam.numArgOuts
  lazy val numTokenOuts:Int = argFringeParam.numTokenOuts
  val isTorus:Boolean
  val isMesh = !isTorus

  object ChannelWidth {
    def empty = new Table[String, String, Int] (
      values=Map(
        "src"->cuTypes, 
        "dst"->cuTypes, 
        "srcDir"->eightDirections, 
        "dstDir"->eightDirections
      ), 
      default=Some(0)
    )
  }

}

case class StaticGridControlNetworkParam(
  isTorus:Boolean=defaultIsTorus
) extends StaticGridNetworkParam[Bit] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // switch to switch channel width
    channelWidth("src"->"sb", "dst"->"sb") = 1

    // switch to CU channel width
    channelWidth("src"->"sb", "dst"->List("pcu", "pmu", "scu")) = 1

    // CU to Switch channel width
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->"sb") = 1

    // CU to CU channel width (Left -> Right)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"W", "dstDir"->"E") = if (option[Boolean]("nn")) 2 else 0
    // CU to CU channel width (Right -> Left)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"E", "dstDir"->"W") = if (option[Boolean]("nn")) 2 else 0
    // CU to CU channel width (Top -> Bottom)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"N", "dstDir"->"E") = 0
    // CU to CU channel width (Bottom -> Top)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"N", "dstDir"->"E") = 0

    // DAG to switch channel width
    channelWidth("src"->"dag", "dst"->"sb") = 1

    // switch to DAG channel width
    channelWidth("src"->"sb", "dst"->"dag") = 1

    // switch to MC channel width
    channelWidth("src"->"sb", "dst"->"mc") = 1

    // MC to switch channel width
    channelWidth("src"->"mc", "dst"->"sb") = 2

    // MC to DAG channel width
    channelWidth("src"->"mc", "dst"->"dag") = 2
      
    // OCU to switch channel width
    channelWidth("src"->"ocu", "dst"->"sb") = 2
    // switch to OCU channel width
    channelWidth("src"->"sb", "dst"->"ocu") = 4

    // Top to switch channel width
    channelWidth("src"->"arg", "dst"->"sb") = 1
    // switch to Top channel width
    channelWidth("src"->"sb", "dst"->"arg") = numTokenOuts
    channelWidth
  }
}

case class StaticGridScalarNetworkParam(
  isTorus:Boolean=defaultIsTorus
) extends StaticGridNetworkParam[Word] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // switch to switch channel width
    channelWidth("src"->"sb", "dst"->"sb") = option[String]("slink")

    // switch to CU channel width
    channelWidth("src"->"sb", "dst"->List("pcu", "scu")) = 2

    // CU to Switch channel width
    channelWidth("src"->List("pcu", "scu"), "dst"->"sb") = 1

    // switch to PMU channel width
    channelWidth("src"->"sb", "dst"->List("pmu")) = 2

    // PMU to Switch channel width
    channelWidth("src"->List("pmu"), "dst"->"sb") = 1

    // CU to CU channel width (Left -> Right)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"W", "dstDir"->"E") = if (option[Boolean]("nn")) 2 else 0
    // CU to CU channel width (Right -> Left)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"E", "dstDir"->"W") = if (option[Boolean]("nn")) 2 else 0
    // CU to CU channel width (Top -> Bottom)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"N", "dstDir"->"E") = 0
    // CU to CU channel width (Bottom -> Top)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"N", "dstDir"->"E") = 0

    // switch to DAG channel width
    channelWidth("src"->"sb", "dst"->"dag") = 4

    // DAG to switch channel width
    channelWidth("src"->"dag", "dst"->"sb") = 1

    // switch to MC channel width
    channelWidth("src"->"sb", "dst"->"mc") = 3

    // MC to switch channel width
    channelWidth("src"->"mc", "dst"->"sb") = 1

    // DAG to MC channel width
    channelWidth("src"->"dag", "dst"->"mc") = 2

    //// switch to OCU channel width
    channelWidth("src"->"sb", "dst"->"ocu") = 5

    //// Top to switch channel width
    channelWidth("src"->"arg", "dst"->"sb") = numArgIns

    //// switch to Top channel width
    channelWidth("src"->"sb", "dst"->"arg") = numArgOuts
    channelWidth
  }
}

case class StaticGridVectorNetworkParam(
  isTorus:Boolean=defaultIsTorus
) extends StaticGridNetworkParam[Vector] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // switch to switch channel width
    channelWidth("src"->"sb", "dst"->"sb") = option[Int]("vlink")

    // switch to CU channel width
    channelWidth("src"->"sb", "dst"->List("pcu")) = 1

    // CU to Switch channel width
    channelWidth("src"->List("pcu"), "dst"->"sb") = 1

    // switch to PMU channel width
    channelWidth("src"->"sb", "dst"->List("pmu")) = 1

    // PMU to Switch channel width
    channelWidth("src"->List("pmu"), "dst"->"sb") = 1

    // CU to CU channel width (Left -> Right)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"W", "dstDir"->"E") = if (option[Boolean]("nn")) 2 else 0
    // CU to CU channel width (Right -> Left)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"E", "dstDir"->"W") = if (option[Boolean]("nn")) 2 else 0
    // CU to CU channel width (Top -> Bottom)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"N", "dstDir"->"E") = 0
    // CU to CU channel width (Bottom -> Top)
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu"), "srcDir"->"N", "dstDir"->"E") = 0

    // switch to MC channel width
    channelWidth("src"->"sb", "dst"->"mc") = 1

    // MC to switch channel width
    channelWidth("src"->"mc", "dst"->"sb") = 1
    channelWidth
  }
}
