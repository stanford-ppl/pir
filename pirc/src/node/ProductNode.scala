package prism.node

import prism._
import prism.util._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

abstract class ProductNode[N<:Node[N]](id:Int, designOpt:Option[Design])(implicit ev:ClassTag[N]) extends Node(id) with Product { self:N =>
  def this(id:Int)(implicit ev:ClassTag[N]) = this(id, None)
  def this()(implicit design:Design, ev:ClassTag[N]) = {
    this(design.nextId, Some(design))
  }

  lazy val design:Design = designOpt match {
    case Some(design) => design
    case None => this.asInstanceOf[Design]
  }

  def productName = s"$productPrefix$id(${values.mkString(",")})" 

  lazy val fieldNames = self.getClass.getDeclaredFields.filterNot(_.isSynthetic).map(_.getName).toList //TODO
  def values = productIterator.toList.zip(stagedFields).map { case (field, staged) => evaluateFields(field, staged) }

  def stage:List[Any] = {
    if (design.staging) 
      productIterator.toList.zipWithIndex.map{ case (field, i) => connectFields(field, i)(design)}
    else Nil
  }

  val stagedFields = stage

  def newInstance[T](args:List[Any], staging:Boolean=true):T = {
    if (this.isInstanceOf[Design]) return this.asInstanceOf[T]
    val constructor = this.getClass.getConstructors()(0) 
    val arguments = args :+ design
    val prevStaging = design.staging
    design.staging = staging
    val newNode = try {
      constructor.newInstance(arguments.map(_.asInstanceOf[Object]):_*).asInstanceOf[T]
    } catch {
      case e:java.lang.IllegalArgumentException =>
        errmsg(s"Error during newInstance of node $this")
        errmsg(s"Expected type: ${constructor.getParameterTypes().mkString(",")}")
        errmsg(s"Got type: ${arguments.map(_.getClass).mkString(",")}")
        throw e
      case e:Throwable => throw e
    }
    design.staging = prevStaging
    newNode
  }

  def connectFields(x:Any, i:Int)(implicit design:Design):Any = {
    x match {
      case Some(x) => Some(connectFields(x, i))
      case x:Iterable[_] => x.map(xx => connectFields(xx, i)) 
      case x:Iterator[_] => x.map(xx => connectFields(xx, i)) 
      case x => x
    }
  }

  def evaluateFields(f:Any, x:Any):Any = {
    (f, x) match {
      case (Some(f), Some(x:Edge[_])) if !x.isConnected => None
      case (Some(f), Some(x)) => Some(evaluateFields(f, x))
      case (f:Iterable[_], x:Iterable[_]) => 
        f.zip(x).flatMap { 
          case (f, x:Edge[_]) if !x.isConnected => None
          case (f,x) => Some(evaluateFields(f,x))
        }
      case (f:Iterable[_], x:Iterator[_]) => 
        f.zip(x.toList).flatMap { 
          case (f, x:Edge[_]) if !x.isConnected => None
          case (f,x) => Some(evaluateFields(f,x))
        }
      case (f, x) => x
    }
  }

}

