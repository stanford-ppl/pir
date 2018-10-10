package spade
package param

import prism.node._
import prism.collection.mutable.Table

import SpadeConfig._
abstract class DynamicGridNetworkParam[B<:PinType:ClassTag] extends NetworkParam[B] {
  lazy val topParam = collectOut[DynamicGridTopParam]().head
  lazy val numRows:Int = topParam.numRows
  lazy val numCols:Int = topParam.numCols
  lazy val numTotalRows = topParam.numTotalRows
  lazy val numTotalCols = topParam.numTotalCols
  lazy val argFringeParam = topParam.fringePattern.argFringeParam
  lazy val numArgIns:Int = argFringeParam.numArgIns
  lazy val numArgOuts:Int = argFringeParam.numArgOuts
  lazy val numTokenOuts:Int = argFringeParam.numTokenOuts
  val numVirtualClasses:Int
  val isTorus:Boolean
  val isMesh = !isTorus

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

case class DynamicGridControlNetworkParam(
  numVirtualClasses:Int = option[Int]("vc"),
  isTorus:Boolean=defaultIsTorus
) extends DynamicGridNetworkParam[Bit] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // router to CU channel width
    channelWidth("src"->"rt", "dst"->List("pcu", "pmu", "scu")) = 4

    // CU to Switch channel width
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->"rt") = 4

    // CU to CU channel width
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu")) = if (option[Boolean]("nn")) 2 else 0

    // DAG to router channel width
    channelWidth("src"->"dag", "dst"->"rt") = 1

    // router to DAG channel width
    channelWidth("src"->"rt", "dst"->"dag") = 1

    // router to MC channel width
    channelWidth("src"->"rt", "dst"->"mc") = 1

    // MC to router channel width
    channelWidth("src"->"mc", "dst"->"rt") = 2

    // MC to DAG channel width
    channelWidth("src"->"mc", "dst"->"dag") = 2
      
    // Top to router channel width
    channelWidth("src"->"arg", "dst"->"rt") = 1
    // router to Top channel width
    channelWidth("src"->"rt", "dst"->"arg") = numTokenOuts
    channelWidth
  }
}

case class DynamicGridScalarNetworkParam(
  numVirtualClasses:Int = option[Int]("vc"),
  isTorus:Boolean=defaultIsTorus
) extends DynamicGridNetworkParam[Word] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // router to CU channel width
    channelWidth("src"->"rt", "dst"->List("pcu", "scu")) = 6

    // CU to Switch channel width
    channelWidth("src"->List("pcu", "scu"), "dst"->"rt") = 4

    // router to PMU channel width
    channelWidth("src"->"rt", "dst"->List("pmu")) = 6

    // PMU to Switch channel width
    channelWidth("src"->List("pmu"), "dst"->"rt") = 4//roundUp(pmuSouts / 4.0)

    // CU to CU channel width
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu")) = if (option[Boolean]("nn")) 2 else 0
    
    // router to DAG channel width
    channelWidth("src"->"rt", "dst"->"dag") = 4

    // DAG to router channel width
    channelWidth("src"->"dag", "dst"->"rt") = 1

    // router to MC channel width
    channelWidth("src"->"rt", "dst"->"mc") = 3

    // MC to router channel width
    channelWidth("src"->"mc", "dst"->"rt") = 1
      
    // DAG to MC channel width
    channelWidth("src"->"dag", "dst"->"mc") = 2
    
    //// router to OCU channel width
    channelWidth("src"->"rt", "dst"->"ocu") = 5
    
    //// Top to router channel width
    channelWidth("src"->"arg", "dst"->"rt") = numArgIns

    //// router to Top channel width
    channelWidth("src"->"rt", "dst"->"arg") = numArgOuts
    channelWidth
  }
}

case class DynamicGridVectorNetworkParam(
  numVirtualClasses:Int = option[Int]("vc"),
  isTorus:Boolean=defaultIsTorus
) extends DynamicGridNetworkParam[Vector] {
  override lazy val channelWidth = {
    val channelWidth = ChannelWidth.empty
    // router to CU channel width
    channelWidth("src"->"rt", "dst"->List("pcu")) = 6

    // CU to Switch channel width
    channelWidth("src"->List("pcu"), "dst"->"rt") = 4

    // router to PMU channel width
    channelWidth("src"->"rt", "dst"->List("pmu")) = 6

    // PMU to Switch channel width
    channelWidth("src"->List("pmu"), "dst"->"rt") = 4

    // CU to CU channel width
    channelWidth("src"->List("pcu", "pmu", "scu"), "dst"->List("pcu", "pmu", "scu")) = if (option[Boolean]("nn")) 2 else 0

    // router to MC channel width
    channelWidth("src"->"rt", "dst"->"mc") = 1
      
    // MC to router channel width
    channelWidth("src"->"mc", "dst"->"rt") = 1
    channelWidth
  }
}

