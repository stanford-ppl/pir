package spade
package param
                          
import SpadeConfig._
sealed trait Pattern extends Parameter

trait GridFringePattern extends Pattern {
  val argFringeParam:ArgFringeParam
  val mcParam:MCParam
  val dagParam:Option[DramAGParam]
}

case class MCOnly(
  argFringeParam:ArgFringeParam=ArgFringeParam(),
  mcParam:MCParam=MCParam(),
  dagParam:Option[DramAGParam]=None
) extends GridFringePattern

case class MC_DramAG(
  argFringeParam:ArgFringeParam=ArgFringeParam(),
  mcParam:MCParam=MCParam(),
  dagParam:Option[DramAGParam]=Some(DramAGParam())
) extends GridFringePattern

trait GridCentrolPattern extends Pattern {
  def cuAt(i:Int, j:Int):CUParam
}

/*
 *
 *  +-----+-----+
 *  | PCU | PMU |
 *  +-----+-----+
 *  | PMU | PCU |
 *  +-----+-----+
 *
 * */
case class Checkerboard (
  pcuParam:PCUParam=PCUParam(),
  pmuParam:PMUParam=PMUParam()
) extends GridCentrolPattern {
  def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) pcuParam else pmuParam 
  }
}
/*
 *
 *  +-----+-----+
 *  | PCU | PMU |
 *  +-----+-----+
 *  | PCU | PMU |
 *  +-----+-----+
 *
 * */
case class ColumnStrip (
  pcuParam:PCUParam=PCUParam(),
  pmuParam:PMUParam=PMUParam()
) extends GridCentrolPattern {
  def cuAt(i:Int, j:Int) = {
    if (j % 2 == 0) pcuParam else pmuParam 
  }
}
/*
 *
 *  +-----+-----+-----+
 *  | PMU | PCU | PMU |
 *  +-----+-----+-----+
 *  | PMU | PCU | PMU |
 *  +-----+-----+-----+
 *
 * */
case class MCMColumnStrip (
  pcuParam:PCUParam=PCUParam(),
  pmuParam:PMUParam=PMUParam()
) extends GridCentrolPattern {
  def cuAt(i:Int, j:Int) = {
    if (i % 3 % 2 == 0) pmuParam else pcuParam
  }
}
/*
 *
 *  +-----+-----+
 *  | PCU | PCU |
 *  +-----+-----+
 *  | PMU | PMU |
 *  +-----+-----+
 *
 * */
case class RowStrip (
  pcuParam:PCUParam=PCUParam(),
  pmuParam:PMUParam=PMUParam()
) extends GridCentrolPattern {
  def cuAt(i:Int, j:Int) = {
    if (i % 2 == 0) pcuParam else pmuParam 
  }
}
/*
 *
 *  +-----+-----+
 *  | PCU | PMU |
 *  +-----+-----+
 *  | SCU | SCU |
 *  +-----+-----+
 *
 * */
case class MixAll (
  pcuParam:PCUParam=PCUParam(),
  pmuParam:PMUParam=PMUParam(),
  scuParam:SCUParam=SCUParam()
) extends GridCentrolPattern {
  def cuAt(i:Int, j:Int) = {
    if (i % 2 == 0) {
      if (j % 2 == 0) pcuParam else pmuParam
    } else scuParam
  }
}
/*
 *
 *  +-----+-----+
 *  | PCU | PMU |
 *  +-----+-----+
 *  | PMU | SCU |
 *  +-----+-----+
 *
 * */
case class HalfAndHalf (
  pcuParam:PCUParam=PCUParam(),
  pmuParam:PMUParam=PMUParam(),
  scuParam:SCUParam=SCUParam()
) extends GridCentrolPattern {
  def cuAt(i:Int, j:Int) = {
    if (i % 2 == 0) if (j % 2 == 0) pcuParam else pmuParam
    else if (j % 2 == 0) pmuParam else scuParam
  }
}

trait CMeshPattern extends Pattern {
  lazy val topParam = collectOut[CMeshTopParam]().head
  val pcuParam:PCUParam
  val pmuParam:PMUParam
  val mcParam:MCParam
  val dagParam:Option[DramAGParam]
  val argFringeParam:ArgFringeParam
  def cuAt(i:Int, j:Int)(ii:Int, jj:Int):Parameter
}

/*
 *  First column
 *  +-----+-----+
 *  | MC  | PMU |
 *  +-----+-----+
 *  | DAG | PCU |
 *  +-----+-----+
 *
 *  Middle columns
 *  +-----+-----+
 *  | PCU | PMU |
 *  +-----+-----+
 *  | PMU | PCU |
 *  +-----+-----+
 *
 *  Last column
 *  +-----+-----+
 *  | PCU | DAG |
 *  +-----+-----+
 *  | PMU | MC  |
 *  +-----+-----+
 * */
case class CMeshCheckerboard(
  pcuParam:PCUParam=PCUParam(),
  pmuParam:PMUParam=PMUParam(),
  mcParam:MCParam=MCParam(),
  dagParam:Option[DramAGParam]=if (option[Boolean]("dag")) Some(DramAGParam()) else None,
  argFringeParam:ArgFringeParam=ArgFringeParam()
) extends CMeshPattern {
  def cuAt(i:Int, j:Int)(ii:Int, jj:Int):Parameter = {
    import topParam._
    if ((ii + jj) % 2 == 0) {
      if (i==0 && ii==0) // first col
        mcParam
      else if (i==numCols-1 && ii==1) // last col
        mcParam
      else
        pcuParam
    } else {
      if (i==0 && ii==0) // first col
        dagParam.getOrElse(pcuParam) 
      else if (i==numCols-1 && ii==1) // last col
        dagParam.getOrElse(pcuParam)
      else
        pmuParam
    }
  }
}
