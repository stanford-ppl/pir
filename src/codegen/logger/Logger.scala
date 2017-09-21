package pir.codegen

import pir._
import pir.pass.Pass

import spade.Spade

import pirc._
import pirc.exceptions._

import java.io.PrintWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.io.ByteArrayOutputStream
import scala.collection.mutable.Stack

trait Logger extends Printer {
  implicit val self:Logger = this
  def debug = Config.debug
  override def pprint(s:String):Unit = if (debug) { super.pprint(s); flush } 
  override def pprintln(s:String):Unit = if (debug) { super.pprintln(s); flush } 
  override def pprintln:Unit = if (debug) { super.pprintln; flush } 

  def promp(header:Option[String], s:Any) = s"${header.fold("") { h => s"[$h] "}}$s"

  override def emitBlock[T](bs:Option[String], b:Option[Braces], es:Option[()=>String])(block: =>T):T = { 
    emitBSln(bs, b, None)
    val res = block
    if (res!=(())) dprintln(s"result=$res")
    emitBEln(None, b, es.map(es => es()))
    res
  }
  def emitBlock[T](header:String, s:String)(block: =>T):T = emitBlock(promp(Some(header), s))(block)

  def dprintln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emitln(promp(header, s))
  def dprint(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emit(promp(header, s))
  def dprintln(pred:Boolean, header:String, s:Any):Unit = dprintln(pred, Some(header), s) 
  def dprint(pred:Boolean, header:String, s:Any):Unit = dprint(pred, Some(header), s) 
  def dprintln(header:String, s:Any):Unit = dprintln(debug, header, s) 
  def dprint(header:String, s:Any):Unit = dprint(debug, header, s) 
  def dprintln(pred:Boolean, s:Any):Unit = dprintln(pred, None, s) 
  def dprint(pred:Boolean, s:Any):Unit = dprintln(pred, None, s)
  def dprintln(s:Any):Unit = dprintln(debug, None, s) 
  def dprint(s:Any):Unit = dprintln(debug, None, s)
  def dbsln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emitBSln(promp(header,s))
  def dbeln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emitBEln(promp(header, s))

  //override def newStream(dp:String, fname:String):FileOutputStream = { 
    //this match {
      //case p:Pass => super.newStream(dp, s"${p.runId}.${fname}")
      //case _ => super.newStream(dp, fname)
    //}
  //}
}
