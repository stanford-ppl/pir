package prism
package codegen

import prism.graph._

trait ScalaCodegen extends Codegen {
  override def emitComment(s:String) = {
    emitln(s"// $s")
  }

  def emitComma(implicit ms:CollectionStatus):Unit = { 
    if (ms.inScope) { 
      if (ms.firstPair) ms.firstPair = false 
      else writeln(s",")
    }
  }

  def emitComma(s:String)(implicit ms:CollectionStatus):Unit = { 
    emitComma
    emit(s)
  }

  def emitInst(s:String)(block: CollectionStatus=>Unit)(e:String)(implicit ms:CollectionStatus=new CollectionStatus()):Unit = { 
    emitComma;
    emitBlock(s, None, Some(e), Parentheses) {
      block(new CollectionStatus())
      writeln("")
    }
  }

  def emitLambda[T](s:String)(block: =>T):T = { 
    emitBlock(s=s, ss=None, es=None)(block)
  }

  def emitLambda[T](s:String, ss:String)(block: =>T):T = { 
    emitBlock(s=s, ss=Some(ss), es=None)(block)
  }

  def emitLambda[T](s:String, ss:String, es:String)(block: =>T):T = { 
    emitBlock(s=s, ss=Some(ss), es=Some(es))(block)
  }

  def emitBlock[T](s:String, ss:Option[String]=None, es:Option[String]=None, b:Braces=CurlyBraces)(block: =>T):T = { 
    emitBS(s, b)
    ss.foreach { ss => write(s" $ss =>") }
    emitln
    val res = block
    emitBE(b)
    es.foreach { es => write(es) }
    emitln
    res
  }

  def emitCommentBlock[T](s:String)(block: =>T):T = { emitBSln(s"// $s "); val res = block; emitBE("// "); emitln; res }

  //def emitCaseClassInst[N<:ProductNode[N]](n:N) = {
    //val fields = n.fieldNames.zip(n.values).map{ case (name, value) =>
      //s"$name=${quote(value)}"
    //}.mkString(",")
    //emitln(s"val ${quote(n)} = ${n.className}(${fields})")
  //}
}
