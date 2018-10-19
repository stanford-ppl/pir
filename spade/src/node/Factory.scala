package spade
package node

import prism.graph._

import param2._

trait Factory extends DefaultParamLoader with BuildEnvironment with TopologicalTraversal {

  type T = Any 

  val forward = true

  override def isVisited(n:N) = false
  
  def create(param:Parameter):SpadeNode = traverseNode(topParam, None).asInstanceOf[Top]

  type CUArray = List[List[ListBuffer[_]]]

  override def visitNode(param:N, prev:T):T = 
    throw new Exception(s"Don't know how to create node for $param")

}

trait BaseFactory extends Factory with TopFactory with NetworkFactory

trait TopFactory extends Factory {
  override def visitNode(param:N, prev:T):T = param match {
    case param:TopParam =>
      Top(param) within {
        super.visitNode(param, None)
      }
    case param@AsicPattern() => None
    case param@Checkerboard(row, col, cu1, cu2, fringeParam, networkParams) =>
      val center = List.tabulate(col, row) { case (x,y) =>
        ListBuffer(visitNode(param.cuAt(x,y), None))
      }
      val array = visitNode(fringeParam, center).asInstanceOf[CUArray]
      //array.zipWithIndex.foreach { case (col, x) =>
        //array.zipWithIndex.foreach { case (nodes, y) =>
          //nodes.foreach { node => 
            //node.indices = List(x,y)
          //}
        //}
      //}
      val connectors = param.networkParams.foreach { param =>
        visitNode(param, array)
      }
      (array, connectors)
    case param:FringeParam =>
      val center = prev.asInstanceOf[CUArray]
      val row = center.head.size
      def createFringeColumn(side:String, array:CUArray) = {
        if (param.shareNode) {
          List.tabulate(param.fringeColumn, row) { case (x,y) =>
            val mc = visitNode(param.mcParam, None)
            val dag = param.dagParam.map(visitNode(_,None))
            ListBuffer(mc) ++ dag
          }
        } else {
          List.tabulate(param.fringeColumn, row) { case (x,y) =>
            (side,x) match {
              case ("left", 0) => ListBuffer(visitNode(param.mcParam, None))
              case ("right", 1) => ListBuffer(visitNode(param.mcParam, None))
              case _ => ListBuffer(visitNode(param.dagParam.get, None))
            }
          }
        }
      }
      val array = createFringeColumn("right", createFringeColumn("left", center))
      array(array.size/2)(array.head.size-1) += visitNode(param.argFringeParam, None)
      array
    case param:CUParam => CU(param) within {
      super.visitNode(param, None)
    }
    case param:ArgFringeParam => ArgFringe(param)
    case param:MCParam => MC(param)
    //case param:TopParam =>
    case param => super.visitNode(param, prev)
  }
}

trait NetworkFactory extends Factory {
  override def visitNode(param:N, prev:T) = param match {
    //case param:TopParam =>
    case param => super.visitNode(param, prev)
  }
}

case class Top(param:TopParam) extends SpadeNode
case class CU(param:CUParam) extends SpadeNode
case class ArgFringe(param:ArgFringeParam) extends SpadeNode
case class MC(param:MCParam) extends SpadeNode
