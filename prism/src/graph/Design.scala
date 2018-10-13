package prism
package graph

import scala.collection.mutable

trait Design extends Serializable {
  implicit val design:this.type = this
  private var nextSym = 1
  def nextId = {nextSym += 1; nextSym }
}
