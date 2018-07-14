package pir
package node

import scala.collection.mutable.Stack

case class PIRDesign() extends prism.node.Design { 

  val pirmeta = new PIRMetadata

  val parentStack = Stack[Container]()
  val ctrlStack = Stack[Controller]()

  val top = Top()

}
