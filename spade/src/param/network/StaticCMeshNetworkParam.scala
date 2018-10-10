package spade
package param

import prism.node._
import prism.collection.mutable.Table

import SpadeConfig._
abstract class StaticCMeshNetworkParam[B<:PinType:ClassTag] extends NetworkParam[B] {
  lazy val topParam = collectOut[CMeshTopParam]().head
  lazy val numRows:Int = topParam.numRows
  lazy val numCols:Int = topParam.numCols
  lazy val argFringeParam = topParam.pattern.argFringeParam
  lazy val numArgIns:Int = argFringeParam.numArgIns
  lazy val numArgOuts:Int = argFringeParam.numArgOuts
  lazy val numTokenOuts:Int = argFringeParam.numTokenOuts

  object ChannelWidth {
    def empty = new Table[String, String, Int] (
      values=Map(
        "src"->cuTypes, 
        "dst"->cuTypes
      ), 
      default=Some(0)
    )
  }
}

case class StaticCMeshControlNetworkParam() extends StaticCMeshNetworkParam[Bit] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // switch to switch channel width
    channelWidth("src"->"sb", "dst"->"sb") = 6

    // switch - CU channel width
    channelWidth("src"->"sb", "dst"->List("pcu", "pmu", "scu")) = 2
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->"sb") = 2

    // CU - CU channel width
    channelWidth("src"->List("pcu", "pmu", "scu","mc"), "dst"->List("pcu", "pmu", "scu")) = if (option[Boolean]("nn")) 2 else 0

    // DAG - switch channel width
    channelWidth("src"->"dag", "dst"->"sb") = 1
    channelWidth("src"->"sb", "dst"->"dag") = 1

    // switch - MC channel width
    channelWidth("src"->"sb", "dst"->"mc") = 1
    channelWidth("src"->"mc", "dst"->"sb") = 2

    // MC - DAG channel width
    channelWidth("src"->"mc", "dst"->"dag") = 2
      
    // Top - switch channel width
    channelWidth("src"->"arg", "dst"->"sb") = 1
    // switch - Top channel width
    channelWidth("src"->"sb", "dst"->"arg") = numTokenOuts

    channelWidth
  }
}

case class StaticCMeshScalarNetworkParam() extends StaticCMeshNetworkParam[Word] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // switch - switch channel width
    channelWidth("src"->"sb", "dst"->"sb") = 4

    // CU - Switch channel width
    channelWidth("src"->"sb", "dst"->List("pcu", "scu")) = 4
    channelWidth("src"->List("pcu", "scu"), "dst"->"sb") = 4
    channelWidth("src"->"sb", "dst"->List("pmu")) = 4
    channelWidth("src"->List("pmu"), "dst"->"sb") = 4

    // CU - CU channel width
    channelWidth("src"->List("pcu", "pmu", "scu","mc"), "dst"->List("pcu", "pmu", "scu")) = if (option[Boolean]("nn")) 2 else 0

    // switch - DAG channel width
    channelWidth("src"->"sb", "dst"->"dag") = 4
    channelWidth("src"->"dag", "dst"->"sb") = 1

    // switch - MC channel width
    channelWidth("src"->"sb", "dst"->"mc") = 3
    channelWidth("src"->"mc", "dst"->"sb") = 1

    // DAG - MC channel width
    channelWidth("src"->"dag", "dst"->"mc") = 2

    //// switch - Top channel width
    channelWidth("src"->"sb", "dst"->"arg") = numArgOuts
    channelWidth("src"->"arg", "dst"->"sb") = numArgIns

    channelWidth
  }
}

case class StaticCMeshVectorNetworkParam() extends StaticCMeshNetworkParam[Vector] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // switch - switch channel width
    channelWidth("src"->"sb", "dst"->"sb") = 4

    // switch - CU channel width
    channelWidth("src"->"sb", "dst"->List("pcu")) = 6
    channelWidth("src"->List("pcu"), "dst"->"sb") = 6
    channelWidth("src"->"sb", "dst"->List("pmu")) = 6
    channelWidth("src"->List("pmu"), "dst"->"sb") = 6

    // CU - CU channel width
    channelWidth("src"->List("pcu", "pmu", "scu","mc"), "dst"->List("pcu", "pmu", "scu")) = if (option[Boolean]("nn")) 2 else 0

    // switch - MC channel width
    channelWidth("src"->"sb", "dst"->"mc") = 1
    channelWidth("src"->"mc", "dst"->"sb") = 1

    channelWidth
  }
}
