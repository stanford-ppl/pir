package pir

import graph._
import pass._
import mapper._
import pir.codegen._
import plasticine.config._
import pir.util._
import pir.exceptions._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.simulation._

//import analysis._

import scala.util.{Try, Success, Failure}

import scala.language.implicitConversions
import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack
import scala.collection.mutable.{Set,Map}
import java.nio.file.{Paths, Files}
import scala.io.Source

trait PIRApp extends Design {
  //override val arch:Spade = SN_4x4
  override val arch:Spade = SN_2x2
  override def toString = this.getClass().getSimpleName().replace("$","")

  def main(args: String*)(top:Top): Any 
  def main(args: Array[String]): Unit = {
    println(args.mkString(", "))
    reset
    top = Top().updateBlock(main(args:_*)) 
    endInfo(s"Finishing graph construction for ${this}")
    run
  }
}

