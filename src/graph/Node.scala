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
class Node(val name:Option[String], val typeStr:String) (implicit design: Design) { 
	design.addNode(this)
  val id : Int = design.nextId
  val title: String = s"${typeStr}${id}${if(name.isDefined) "_" else ""}${name.getOrElse("")}"

  override def toString = title 

  val DefaultPrecision = 32

}

