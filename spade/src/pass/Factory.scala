package spade
package pass

import spade.node._
import spade.param._
import prism.graph._

import param._

class SpadeBaseFactory(implicit compiler:Spade) extends SpadePass with BaseFactory {
  override def runPass = {
    compiler._spadeTop = Some(create(compiler.loadParam))
  }

}

trait Factory extends Env with DFSTopologicalTraversal with ParamTraversal {

  type T = Any 
  def zero = None

  val forward = false

  def create[T](param:Parameter):T = {
    resetTraversal
    traverseNode(param, None).asInstanceOf[T]
  }

  type CUArray = List[List[ListBuffer[Terminal]]]
  type ConnectorArray = List[List[_]]

  def visitNodeAs[M](n:N, prev:T):M = visitNode(n,prev).asInstanceOf[M]

  implicit class Parent(val value:SpadeNode) extends State[SpadeNode] {
    def initNode[N<:Node[N]](n:Node[N], value:SpadeNode) = {
      n.setParent(value.as)
    }
  }

}

trait BaseFactory extends Factory with TopFactory with NetworkFactory {
  override def visitNode(param:N, prev:T):T = dbgblk(s"create($param)"){
    super.visitNode(param, prev)
  }
}

trait TopFactory extends Factory {
  val scale = 3
  override def visitNode(param:N, prev:T):T = param match {
    case param:TopParam =>
      val top = new Top(param)
      within(top) {
        super.visitNode(param, None)
      }
      top
    case param:AsicPattern => None
    case param:InfinatePattern =>
      val pcu = visitNodeAs[CU](param.cu1, None)
      val pmu = visitNodeAs[CU](param.cu2, None)
      val center = List.tabulate(1,2) { case (0,0) => ListBuffer(pcu); case (0,1) => ListBuffer(pmu); case _ => ListBuffer(pcu) }
      val array = visitNodeAs[CUArray](param.fringePattern, center)
      (array, center)
    case param@Checkerboard(row, col, cu1, cu2, fringePattern, networkParams) =>
      val center = List.tabulate(col, row) { case (x,y) =>
        ListBuffer(visitNodeAs[CU](param.cuAt(x,y), None))
      }
      val array = visitNodeAs[CUArray](fringePattern, center)
      array.view.zipWithIndex.foreach { case (col, x) =>
        col.view.zipWithIndex.foreach { case (nodes, y) =>
          nodes.view.zipWithIndex.foreach { case (node, i) => 
            node.pos((x*scale*1.0,(y+0.4*i)*scale))
          }
        }
      }
      val connectors = {
        array.zipWithIndex.map { case (col, x) =>
          col.zipWithIndex.map { case (row, y) =>
            (new Connector()).pos(((x+0.4)*scale,(y+0.4)*scale))
          }
        }
      }
      param.networkParams.foreach { param =>
        visitNode(param, (array, connectors))
      }
      (array, connectors)
    case param:FringePattern =>
      val center = prev.asInstanceOf[CUArray]
      val row = center.head.size
      def createFringeColumn(side:String, array:CUArray):CUArray = {
        val fringe:CUArray = if (param.shareNode) {
          List.tabulate(param.fringeColumn, row) { case (x,y) =>
            val mc = visitNodeAs[MC](param.mcParam, None)
            val dag = param.dagParam.map(visitNodeAs[CU](_,None))
            ListBuffer(mc) ++ dag
          }
        } else {
          List.tabulate(param.fringeColumn, row) { case (x,y) =>
            (side,x) match {
              case ("left", i) if i % 2 ==0 => ListBuffer(visitNodeAs[MC](param.mcParam, None))
              case ("right", i) if i % 2 == 1 => ListBuffer(visitNodeAs[MC](param.mcParam, None))
              case _ => ListBuffer(visitNodeAs[CU](param.dagParam.get, None))
            }
          }
        }
        if (side == "left") fringe ++ array else array ++ fringe
      }
      val argFringe = visitNodeAs[ArgFringe](param.argFringeParam, None)
      val array = createFringeColumn("right", createFringeColumn("left", center))
      array(array.size/2)(array.head.size-1) += argFringe
      array
    case param:CUParam => new CU(param) /*within {*/
      //super.visitNode(param, None)
    /*}*/
    case param:ArgFringeParam => new ArgFringe(param)
    case param:MCParam => new MC(param)
    case param => super.visitNode(param, prev)
  }
}

trait NetworkFactory extends Factory {
  override def visitNode(param:N, prev:T) = param match {
    case param:NetworkParam => 
      val (a,b) = prev
      val array = a.asInstanceOf[CUArray]
      val connectors = b.asInstanceOf[ConnectorArray]
      (array, connectors).zipped.foreach { case (col1,col2) =>
        (col1,col2).zipped.foreach { case (cus, connector) =>
          val connectorBundles = within(connector.asInstanceOf[SpadeNode]) {
            (new DynamicBundle(param.bundleParam),
            new StaticBundle(param.bundleParam))
          }
          cus.foreach { cu =>
            within(cu.asInstanceOf[SpadeNode]) {
              new DynamicBundle(param.bundleParam)
              new StaticBundle(param.bundleParam)
            }
          }
        }
      }
      // TODO: connnect between bundles
    case param => super.visitNode(param, prev)
  }
}
