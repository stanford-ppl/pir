package dhdl.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import dhdl.Design
import dhdl.graph._

/** Base class for all DHDL nodes. This class contains
  * implementations of common methods that every DHDL node
  * can use and override
  */
class Node(nameStr:Option[String], typeStr:String) (implicit design: Design) { 
	design.addNode(this)
  val name: String = nameStr.getOrElse("")
  val id : Int = design.nextId
  if (nameStr.isDefined) design.addName(this, name)

  override def toString = s"${typeStr}${id}${if(nameStr.isDefined) "_" else ""}${name}"

  val DefaultPrecision = 32
}


