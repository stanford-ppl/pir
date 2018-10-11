package prism
package node

trait ProductNode[N<:Node[N]] extends Node[N] with Product { self:N =>

  def isStaging = design.staging

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
    val arguments = args :+ design
    val prevStaging = design.staging
    design.staging = staging
    val newNode = newInstance(arguments)
    design.staging = prevStaging
    newNode
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
              err(s"Error during newInstance of node $this, staging=$staging", exception=false)
              err(s"Expected type: ${constructor.getParameterTypes().mkString(",")}", exception=false)
              err(s"Got type: ${arguments.map(_.getClass).mkString(",")}", exception=false)
              throw e
            case e:java.lang.reflect.InvocationTargetException =>
              err(s"InvocationTargetException during newInstance of node $this staging=$staging", exception=false)
              err(s"arguments=$arguments", exception=false)
              err(s"Cause:", exception=false)
              err(s"${e.getCause}", exception=false)
              throw e
            case e:Throwable => throw e
          }
      }).asInstanceOf[T]
    }
  }

  def addField[T](x:T):T = { connectFields(x, -1); x }

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

