package spade
package param

import prism.node._
import prism.collection.mutable.Table

import SpadeConfig._
abstract class DynamicCMeshNetworkParam[B<:PinType:ClassTag] extends NetworkParam[B] {
  lazy val topParam = collectOut[DynamicCMeshTopParam]().head
  lazy val numRows:Int = topParam.numRows
  lazy val numCols:Int = topParam.numCols
  lazy val numTotalRows = topParam.numTotalRows
  lazy val numTotalCols = topParam.numTotalCols
  lazy val argFringeParam = topParam.pattern.argFringeParam
  lazy val numArgIns:Int = argFringeParam.numArgIns
  lazy val numArgOuts:Int = argFringeParam.numArgOuts
  lazy val numTokenOuts:Int = argFringeParam.numTokenOuts
  val numVirtualClasses:Int

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

case class DynamicCMeshControlNetworkParam(
  numVirtualClasses:Int = option[Int]("vc")
) extends DynamicCMeshNetworkParam[Bit] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // switch to switch channel width
    channelWidth("src"->"rt", "dst"->"rt") = 6

    // switch - CU channel width
    channelWidth("src"->"rt", "dst"->List("pcu", "pmu", "scu")) = 2
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->"rt") = 2

    // CU - CU channel width
    channelWidth("src"->List("pcu", "pmu", "scu","mc"), "dst"->List("pcu", "pmu", "scu")) = if (option[Boolean]("nn")) 2 else 0

    // DAG - switch channel width
    channelWidth("src"->"dag", "dst"->"rt") = 1
    channelWidth("src"->"rt", "dst"->"dag") = 1

    // switch - MC channel width
    channelWidth("src"->"rt", "dst"->"mc") = 1
    channelWidth("src"->"mc", "dst"->"rt") = 2

    // MC - DAG channel width
    channelWidth("src"->"mc", "dst"->"dag") = 2
      
    // Top - switch channel width
    channelWidth("src"->"arg", "dst"->"rt") = 1
    // switch - Top channel width
    channelWidth("src"->"rt", "dst"->"arg") = numTokenOuts

    channelWidth
  }
}

case class DynamicCMeshScalarNetworkParam(
  numVirtualClasses:Int = option[Int]("vc")
) extends DynamicCMeshNetworkParam[Word] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // switch - switch channel width
    channelWidth("src"->"rt", "dst"->"rt") = 4

    // CU - Switch channel width
    channelWidth("src"->"rt", "dst"->List("pcu", "scu")) = 4
    channelWidth("src"->List("pcu", "scu"), "dst"->"rt") = 4
    channelWidth("src"->"rt", "dst"->List("pmu")) = 4
    channelWidth("src"->List("pmu"), "dst"->"rt") = 4

    // CU - CU channel width
    channelWidth("src"->List("pcu", "pmu", "scu","mc"), "dst"->List("pcu", "pmu", "scu")) = if (option[Boolean]("nn")) 2 else 0

    // switch - DAG channel width
    channelWidth("src"->"rt", "dst"->"dag") = 4
    channelWidth("src"->"dag", "dst"->"rt") = 1

    // switch - MC channel width
    channelWidth("src"->"rt", "dst"->"mc") = 3
    channelWidth("src"->"mc", "dst"->"rt") = 1

    // DAG - MC channel width
    channelWidth("src"->"dag", "dst"->"mc") = 2

    //// switch - Top channel width
    channelWidth("src"->"rt", "dst"->"arg") = numArgOuts
    channelWidth("src"->"arg", "dst"->"rt") = numArgIns

    channelWidth
  }
}

case class DynamicCMeshVectorNetworkParam(
  numVirtualClasses:Int = option[Int]("vc")
) extends DynamicCMeshNetworkParam[Vector] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // switch - switch channel width
    channelWidth("src"->"rt", "dst"->"rt") = 4

    // switch - CU channel width
    channelWidth("src"->"rt", "dst"->List("pcu")) = 6
    channelWidth("src"->List("pcu"), "dst"->"rt") = 6
    channelWidth("src"->"rt", "dst"->List("pmu")) = 6
    channelWidth("src"->List("pmu"), "dst"->"rt") = 6

    // CU - CU channel width
    channelWidth("src"->List("pcu", "pmu", "scu","mc"), "dst"->List("pcu", "pmu", "scu")) = if (option[Boolean]("nn")) 2 else 0

    // switch - MC channel width
    channelWidth("src"->"rt", "dst"->"mc") = 1
    channelWidth("src"->"mc", "dst"->"rt") = 1

    channelWidth
  }
}

