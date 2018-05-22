package pir
package node

trait PIRNodeUtil extends ContainerUtil with MemoryUtil with DramFringeUtil with StreamFringeUtil
  with GlobalIOUtil with DefUtil with AccessUtil with ComputeNodeUtil with ArgFringeUtil 
  with ControllerUtil {

  def within[T<:PIRNode:ClassTag](n:PIRNode) = {
    n.ancestors.collect { case cu:T => cu }.nonEmpty
  }

  def globalOf(n:PIRNode) = {
    n.collectUp[GlobalContainer]().headOption
  }

  def ctrlsOf(container:Container) = {
    implicit val design = container.design.asInstanceOf[PIRDesign]
    import design.pirmeta._
    container.collectDown[ComputeNode]().flatMap { comp => ctrlOf.get(comp) }.toSet[Controller]
  }
}

