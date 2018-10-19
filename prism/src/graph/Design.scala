package prism
package graph

import scala.collection.mutable

trait Design extends Serializable {
  implicit def design:this.type = this
  @transient private var nextSym = 1

  def nextId = { 
    nextSym += 1
    nextSym
  }
}
