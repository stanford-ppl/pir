package pir.codegen

import pir.graph._
import pir._
import pir.util._
import pir.util.misc._
import pir.pass.Traversal

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File
import pir.util._

class PIRPrinter(fn:String)(implicit design: Design) extends Codegen with Traversal {
  def shouldRun = Config.debug

  def this()(implicit design: Design) = {
    this(Config.pirFile)
  }
  override lazy val stream:OutputStream = newStream(fn) 

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

  def genFields(node:Node):String = PIRPrinter.genFields(node)

  private def toStr(mp:Map[String, String], s:String, i:Reg) = mp += (s -> i.toString)
  private def toStr(mp:Map[String, String], s:String, l:Set[_]) = 
    if (l.size > 0) mp += (s -> s"[${l.mkString(",")}]")
  private def toStr(mp:Map[String, String], s:String, l:ListBuffer[_]) = 
    if (l.size > 0) mp += (s -> s"[${l.mkString(",")}]")
  private def toStr(mp:Map[String, String], s:String, m:Map[_,_]) = 
    if (m.size > 0) {
      val mstrs = m.map{case (k, v) => v match {
          case n:Set[_] => s"${k}->{${n.map{i => s"${i}"}.mkString(",")}}"
          case n:Map[_,_] => s"${k}->{${n.map{case (kk, vv) => s"${vv}"}.mkString(",")}}"
          case _ => s"${k}->${v}"
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

  def emitBlock(title:String, node:Node):Unit = {
    emitBlock(title) {
      node match {
        case n:InnerController =>
          emitBlock(s"mapping =") {
            regMapToStrs(n).foreach { case (k, v) =>
              emitln(s"${k}:${v}")
            }
          }
          emitBlock(s"InfGraph =") {
            n.infGraph.foreach { case (k, v) => 
              emitln(s"${k}: [${v.mkString(s",")}]")
            }
          }
          super.visitNode(node)
        case n:OuterController =>
          super.visitNode(node)
        case n:Stage =>
          val strs = ListBuffer[String]()
          strs += s"uses:[${n.uses.mkString(",")}]"
          strs += s"defs:[${n.defs.mkString(",")}]"
          emitln(strs.mkString(" "))
          strs.clear
          strs += s"liveIns:[${n.liveIns.mkString(",")}]"
          strs += s"liveOuts:[${n.liveOuts.mkString(",")}]"
          emitln(strs.mkString(" "))
          n.prs.foreach { case pr =>
           emitln(s"pr=${pr}, in=${pr.in.from}, out=[${pr.out.to.mkString}]")
          }
        case _ => super.visitNode(node)
      }
    }
  }

  override def visitNode(node: Node) : Unit = {
    node match {
      case n:Controller => emitBlock(s"${node}${genFields(node)}", node)
      case n:CounterChain => emitBlock(s"${node}${genFields(node)}", node)
      case n:Stage => emitBlock(s"${quote(node)}${genFields(node)}", node)
      case n:UDCounter => // printed in Ctrl.txt
      case n:FIFOOnWrite => emitBlock(s"${node}${genFields(node)}", node)
      case _ => emitln(s"${node}${genFields(node)}")
    }
  }

  override def finPass() = {
    endInfo(s"Finishing PIR Printing in ${getPath}")
    close
  }
}
object PIRPrinter {
  def genFields(node:Node)(implicit design:Design):String = {
  val pirmeta:PIRMetadata = design
  import pirmeta._

    val fields = ListBuffer[String]()
    node.name.foreach { name => fields += s"name=$name" }
    node match {
      case n:Controller =>
        fields += s"children=[${n.children.mkString(",")}]"
        fields += s"isHead=${n.isHead}"
        fields += s"isLast=${n.isLast}"
      case n:Primitive => {
        //fields += s"ctrler=${n.ctrler}"
      }
      case _ =>
    }
    node match {
      case n:ComputeUnit =>
        fields += s"parent=${n.parent}"
        fields += s"consumed=[${n.consumed.mkString(",")}]"
        fields += s"trueConsumed=[${n.trueConsumed.mkString(",")}]"
        fields += s"produced=[${n.produced.mkString(",")}]"
        n match {
          case n:InnerController =>
            fields += s"outers=[${n.outers.mkString(s",")}]"
            n match {
              case n:MemoryController =>
                fields += s"mctpe=${n.mctpe}"
              case _ =>
            }
          case n:OuterController =>
            fields += s"inner=[${n.inner}]"
            fields += s"length=${n.length}"
        }
      case p:CounterChain =>
        fields += s"copy=${p.copy.getOrElse("None")}"
        fields += s"copied=[${p.copied.mkString(",")}]"
      case p:OnChipMem =>
        fields += s"size=${p.size}"
        fields += s"RP=[${p.readPort.to.mkString(",")}], WP=${p.writePort.from}"
        fields += s"banking=${p.banking}"
        p match { case p:SRAMOnRead => fields += s"RA=${p.readAddr.from}"; case _ => }
        p match { case p:SRAMOnWrite => fields += s"WA=${p.writeAddr.from}"; case _ => }
        p match { case p:FIFOOnWrite => fields += s"wtStart=${p.wtStart}, wtEnd=${p.wtEnd}"; case _ => }
        p match { case p:MultiBuffering => fields += s"multiBuffer=${p.buffering}"; case _ => }
      case p:Stage =>
        fields += s"prev=${p.prev}"
        fields += s"next=${p.next}"
        p.fu.foreach { fu =>
          fields += s"operands=[${fu.operands.map(_.from).mkString(",")}]"
          fields += s"op=${fu.op}"
          fields += s"results=[${fu.out.to.mkString(",")}]"
        }
        p match {
          case s:ReduceStage => fields += s"idx=${s.idx}"
          case _ =>
        }
      case p:ScalarIn =>
        fields += s"scalar=${p.scalar}, writer=${p.scalar.writer}"
        vecOf.get(p).foreach { vecIn =>
          fields += s"vecIn=${vecIn}"
        }
      case p:ScalarOut =>
        fields += s"scalar=${p.scalar}, readers=[${p.scalar.readers.mkString(",")}]"
        vecOf.get(p).foreach { vecOut =>
          fields += s"vecOut=${vecOut}"
        }
      case p:VecIn =>
        fields += s"vector=${p.vector}, writer=${p.vector.writer}"
      case p:VecOut =>
        fields += s"vector=${p.vector}, readers=[${p.vector.readers.mkString(",")}]"
      case p:Counter => 
        fields += s"min=${p.min.from}, max=${p.max.from}, step=${p.step.from}"
        fields += s"en=${p.en.from}, done=[${p.done.to.mkString(",")}]"
        fields += s"isInner=${p.isInner}, isOuter=${p.isOuter}"
      //case p:UDCounter => 
      //  fields += s"init=${p.initVal}"
      case r:PipeReg =>
      case r:Const[_] => fields += s"${r.value}"
      case p:Reg => 
      case r:ArgIn =>
      case r:ArgOut =>
      case s:Scalar =>
        val writer = if (s.writer==null) "null" else s.writer.ctrler
        fields += s"writer=${writer} readers=[${s.readers.map(_.ctrler).mkString(",")}]"
        s match { case s:MultiBuffering => fields += s"multiBuffer=${s.buffering}"; case _ => }
      case v:Vector =>
        val writer = if (v.writer==null) "null" else v.writer.ctrler
        fields += s"writer=${writer} readers=[${v.readers.map(_.ctrler).mkString(",")}]" 
        v match {
          case n:DummyVector => fields += s"scalars=(${n.scalars.mkString(",")})" 
          case _ =>
        }
      case _ =>
    }
    s"(${if (fields.size>0) fields.reduce(_+", "+_) else ""})"
  }

}
