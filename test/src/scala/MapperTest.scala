package pir.test

import pir._
import pir.typealias._
import pir.misc._
import pir.graph.enums._
import pir.graph._
import pir.graph.mapper._
import pir.graph.traversal._

import org.scalatest._
import scala.language.reflectiveCalls

class MapperTest extends UnitTest { self =>

  "Mapper Test1" should "success" taggedAs(WIP) in {
    new Design {
      implicit val ctrler:CU = null 
      // Nodes
      // PNodes
      override val arch = null
      // Mapping
      val mapper = new Mapper {}
      // Printer
    }
  }

}

