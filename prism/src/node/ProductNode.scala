package prism.node

import prism._
import prism.util._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

abstract class ProductNode[N<:Node[N]](designOpt:Option[Design])(implicit ev:ClassTag[N]) extends Node[N] with Product { self:N =>
  def this()(implicit design:Design, ev:ClassTag[N]) = this(Some(design))

  val id = designOpt match {
    case Some(design) => design.nextId
    case None => 0 // overritten later
  }

  def design = designOpt.get
  def isStaging = designOpt match {
    case Some(design) => design.staging
    case None => true
  }

  def productName = s"$productPrefix$id(${values.mkString(",")})" 

  lazy val fieldNames = self.getClass.getDeclaredFields.filterNot(_.isSynthetic).map(_.getName).toList //TODO
  def values = fields.zip(stagedFields).map { case (field, staged) => evaluateFields(field, staged) }

  def fields = super.productIterator.toList
  override def productIterator = fields.iterator

  val stagedFields = {
    if (isStaging) 
      fields.zipWithIndex.map{ case (field, i) => connectFields(field, i)}
    else Nil
  }

  def constructArgs[T](args:List[Any], staging:Boolean)(newInstance: List[Any] => T) = {
    designOpt match {
      case Some(design) =>
        val arguments = args :+ design
        val prevStaging = design.staging
        design.staging = staging
        val newNode = newInstance(arguments)
        design.staging = prevStaging
        newNode
      case None =>
        val arguments = args
        newInstance(arguments)
    }
  }

  def newInstance[T](args:List[Any], staging:Boolean=true):T = {
    constructArgs(args, staging) { arguments =>
      (this match {
        case design:Design => design
        case _ => 
          val constructor = this.getClass.getConstructors()(0) 
          try {
            constructor.newInstance(arguments.map(_.asInstanceOf[Object]):_*)
          } catch {
            case e:java.lang.IllegalArgumentException =>
              errmsg(s"Error during newInstance of node $this, staging=$staging")
              errmsg(s"Expected type: ${constructor.getParameterTypes().mkString(",")}")
              errmsg(s"Got type: ${arguments.map(_.getClass).mkString(",")}")
              throw e
            case e:java.lang.reflect.InvocationTargetException =>
              errmsg(s"InvocationTargetException during newInstance of node $this staging=$staging")
              errmsg(s"arguments=$arguments")
              errmsg(s"Cause:")
              errmsg(s"${e.getCause}")
              throw e
            case e:Throwable => throw e
          }
      }).asInstanceOf[T]
    }
  }

  def connectFields(x:Any, i:Int):Any = {
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

