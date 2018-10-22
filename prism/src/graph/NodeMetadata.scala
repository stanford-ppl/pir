package prism
package graph

trait NodeMetadata extends Metadata {

  class Name extends Data[Name, String]
  val name = new Name

  class Position extends Data[Position, (Double,Double)]
  val pos = new Position
}
