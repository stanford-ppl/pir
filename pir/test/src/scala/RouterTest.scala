package pir.test

import pir._
import pir.node._
import pir.mapper._
import pir.pass._
import pir.util.typealias._

import arch._

import pirc.enums._
import pirc.util._

import org.scalatest._
import scala.language.reflectiveCalls
import scala.util.Random
import scala.collection.mutable.ListBuffer

class RouterTest extends UnitTest { self =>

  "RouterTest" should "success" taggedAs(WIP) in {
    new PIR { self =>
      def main(args: Array[String]) = {}
      val arch = SN8x8
      val cus = arch.cuArray
      // Mapping
      val router = new ControlRouter()

      val startCU = cus(0)(0)
      val endCU = cus(3)(3)

      tic
      val routes = router.fwdAdvance(
        start=cus(0)(0),
        validCons=None,
        advanceCons=None,
        end=Some(cus(3)(3)),
        //reached=None,
        minHop=1,
        maxHop=15
      )
      toc(s"advance", "ms")
      println(s"routes:${routes.size}")
    }
  }

}

