package pir.codegen

import pir._
import pir.node._
import pir.pass.Traversal
import pir.util._

import pirc._
import pirc.util._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap


class PIRPrinter(fn:String)(implicit design: PIR) extends Codegen with Traversal with Logger {
  import pirmeta._

  def shouldRun = Config.debug

  def this()(implicit design: PIR) = {
    this(PIRConfig.pirFile)
  }
  override lazy val stream = newStream(fn) 

  override def initPass() = {
    super.initPass
    emitBlock("Scalars") {
      design.top.scalars.foreach { s =>
        emitln(s"${s}${genFields(s)}")
      }
    }
    emitBlock("Vectors") {
      design.top.vectors.foreach { v =>
        emitln(s"${v}${genFields(v)}")
      }
    }
  }

  def genFields(node:Node):String = {
    val fields = PIRPrinter.genFields(node)
    s"(${fields.mkString(",")})"
  }

  private def toStr(r:Any):String = r match { case r:AccumPR => s"${r.toString}(init=${r.getInit})"; case r => r.toString }
  private def toStr(mp:Map[String, String], s:String, r:Reg):Unit = mp += (s -> toStr(r))
  private def toStr(mp:Map[String, String], s:String, l:Set[_<:Reg]):Unit = 
    if (l.size > 0) mp += (s -> s"[${l.map(toStr).mkString(",")}]")
  private def toStr(mp:Map[String, String], s:String, l:ListBuffer[_<:Reg]):Unit = 
    if (l.size > 0) mp += (s -> s"[${l.map(toStr).mkString(",")}]")
  private def toStr(mp:Map[String, String], s:String, m:Map[_,_]):Unit = 
    if (m.size > 0) {
      val mstrs = m.map{case (k, v) => v match {
          case n:Set[_] => 
            s"${k}->{${n.map{i => toStr(i)}.mkString(",")}}"
          case n:Map[_,_] => 
            s"${k}->{${n.map{case (kk, vv) => toStr(vv)}.mkString(",")}}"
          case _ => 
            s"${k}->${v}"
        } 
      }
      mp += (s -> s"[${mstrs.mkString(",")}]")
    }
  def regMapToStrs(c:InnerController):Map[String, String] = {
    var m = HashMap[String, String]()
    toStr(m, "scalarInRegs" , c.scalarInRegs  )
    c match {
      case c:InnerController =>
        toStr(m, "reduceReg" , c.reduceReg  )
        toStr(m, "accumRegs" , c.accumRegs      )
        toStr(m, "vecInRegs" , c.vecInRegs      )
        toStr(m, "vecOutRegs" , c.vecOutRegs     )
        toStr(m, "scalarOuts", c.scalarOutRegs )
        toStr(m, "loadRegs"  , c.loadRegs   )
        toStr(m, "storeRegs" , c.storeRegs  )
        toStr(m, "ctrRegs"   , c.ctrRegs    )
        toStr(m, "tempRegs"  , c.tempRegs   )
      case _ =>
    }
    m
  }

  def emitBlock[T](node:Node)(block: =>T):T = emitBlock(s"${quote(node)} ${genFields(node)}")(block)

  override def visitNode(node: Node) : Unit = {
    node match {
      case n:Controller => emitBlock(node) {
        emitln(s"children=${quote(n.children)}")
        pirmeta.summary(n).foreach(emitln)
        super.visitNode(node)
      }
      case n:Stage => emitBlock(node) {
        super.visitNode(node)
        pirmeta.summerize(node, useOf, defOf, liveInOf, liveOutOf).foreach { info =>
          emitln(s"$info")
        }
      }
      case n:FuncUnit => 
        emitln(s"$n.op=${n.op}")
        super.visitNode(node)
      case n:CtrlBox => emitBlock(node) {
        super.visitNode(n)
      } 
      case n@(_:PipeReg|_:Delay|_:GlobalIO) => emitln(s"$node ${genFields(node)}")
      case n:Input =>
        emitln(s"$n.from=${n.from}")
      case n:Output =>
        emitln(s"$n.to=[${n.to.mkString(",")}]")
      case _ => emitBlock(node) { super.visitNode(node) } 
    }
  }

  override def finPass() = {
    endInfo(s"Finishing PIR Printing in ${getPath}")
    close
  }
}
object PIRPrinter {
  def genFields(node:Node)(implicit design:PIR):List[String] = {
    val pirmeta:PIRMetadata = design
    import pirmeta._

    val fields = ListBuffer[String]()
    //node.name.foreach { name => fields += s"name=$name" }
    node match {
      case n:Controller =>
      case n:Stage => 
        fields ++= pirmeta.summerize(node, parOf)
      case n:Primitive => {
        fields ++= pirmeta.summary(node)
      }
      case _ =>
    }
    node match {
      case n:ComputeUnit =>
        fields += s"parent=${n.parent}"
        n match {
          case n:MemoryController =>
            fields += s"mctpe=${n.mctpe}"
          case _ =>
        }
      case p:CounterChain =>
        fields += s"copy=${p.copy.getOrElse("None")}"
        fields += s"copied=[${p.copied.mkString(",")}]"
      case p:OnChipMem =>
        fields += s"size=${p.size}"
        fields += s"banking=${p.banking}"
        fields += s"buffering=${p.buffering}"
      case p:Stage =>
        fields += s"prev=${p.prev.map(quote)}"
        fields += s"next=${p.next.map(quote)}"
        p match {
          case s:ReduceStage => fields += s"reduceIdx=${s.idx}"
          case _ =>
        }
      case p:PipeReg =>
        fields.clear
        fields += s"in=${p.in.from}, out=[${p.out.to.mkString}]"
      case p:Delay =>
        fields.clear
        fields += s"in=${p.in.from}, out=[${p.out.to.mkString}]"
      case p:GlobalInput =>
        fields += s"variable=${p.variable}, writer=${p.variable.writer}"
      case p:GlobalOutput =>
        fields += s"variable=${p.variable}, readers=[${p.variable.readers.mkString(",")}]"
      case p:UpDownCounter => 
        fields += s"init=${p.initVal}"
      case r:Const[_] => fields += s"${r.value}"
      case r:ArgIn =>
      case r:ArgOut =>
      case s:Scalar =>
        val writer = if (s.writer==null) "null" else s.writer.ctrler
        fields += s"writer=${writer} readers=[${s.readers.map(_.ctrler).mkString(",")}]"
      case v:Vector =>
        val writer = if (v.writer==null) "null" else v.writer.ctrler
        fields += s"writer=${writer} readers=[${v.readers.map(_.ctrler).mkString(",")}]" 
      case _ =>
    }
    fields.toList
  }

}
