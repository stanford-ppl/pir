package pir
package node

trait PIRNodeUtil extends PIRContainer with PIRMemory with PIRDramFringe with PIRGlobalIO 
  with PIRDef with PIRAccess with PIRComputeNode with PIRArgFringe {

  def within[T<:PIRNode:ClassTag](n:PIRNode) = {
    n.ancestors.collect { case cu:T => cu }.nonEmpty
  }

}

