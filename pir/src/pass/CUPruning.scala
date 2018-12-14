//package pir
//package pass

//import pir.node._
//import pir.mapper._
//import prism.graph._

//class CUPruning(implicit compiler:PIR) extends PIRPass with CostUtil with MappingLogger {

  //val constrains = ListBuffer[Constrain]()

  ////override def runPass = {
    ////resetAllCaches
    ////constrains.clear
    ////if (!spadeParam.isAsic) {
      ////constrains += new CUCostConstrain
    ////}
    ////topMap = topMap.flatMap { 
      ////_.map[CUMap] { cumap =>
        ////initCUMap(cumap)
      ////}.flatMap[CUMap] { cumap =>
        ////flatFold(constrains, cumap) { case (cumap, constrain) => constrain.prune(cumap) }
      ////}
    ////}
    ////topMap.left.foreach { fail }
    ////topMap.right.foreach { logging }
  ////}

//}
