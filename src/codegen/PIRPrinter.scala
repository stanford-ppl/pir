package pir.graph.traversal

import pir.graph._
import pir._
import pir.codegen.{Printer, DotCodegen}
import pir.misc._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File

class PIRPrinter(fileName:String)(implicit design: Design) extends DFSTraversal with Printer with Metadata {

  def this()(implicit design: Design) = {
    this(Config.pirFile)
  }
  override val stream:OutputStream = newStream(fileName) 

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
    toStr(m, "scalarIns" , c.scalarIns  )
    c match {
      case c:InnerController =>
        toStr(m, "reduceReg" , c.reduceReg  )
        toStr(m, "vecIns"    , c.vecIns      )
        toStr(m, "vecOut"    , c.vecOut     )
        toStr(m, "scalarOuts", c.scalarOuts )
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
          n.prs.foreach { case (reg, pr) =>
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
      case n:Stage => emitBlock(s"${DotCodegen.quote(node)}${genFields(node)}", node)
      case n:UDCounter => // printed in Ctrl.txt
      case _ => emitln(s"${node}${genFields(node)}")
    }
  }

  override def finPass() = {
    info(s"Finishing PIR Printing in ${getPath}")
    close
  }
}
object PIRPrinter extends Metadata {
  def genFields(node:Node)(implicit design:Design):String = {
    val fields = ListBuffer[String]()
    node match {
      case n:Controller =>
        fields += s"children=[${n.children.mkString(",")}]"
      case n:Primitive => {
        //fields += s"ctrler=${n.ctrler}"
      }
      case _ =>
    }
    node match {
      case n:ComputeUnit =>
        fields += s"parent=${n.parent}"
        fields += s"dep=[${n.dependencies.mkString(",")}]"
        fields += s"deped=[${n.dependeds.mkString(",")}]"
        n match {
          case n:InnerController =>
            fields += s"outers=[${n.outers.mkString(s",")}]"
            n match {
              case n:MemoryController =>
                fields += s"mctpe=${n.mctpe}"
              case _ =>
            }
            fields += s"writtenMem=[${n.writtenMem}]"
          case n:OuterController =>
            fields += s"inner=[${n.inner}]"
            fields += s"height=${n.height}"
        }
        n match {
          case c:TileTransfer =>
            fields += s"mctpe=${c.mctpe}"
          case _ =>
        }
      case p:CounterChain =>
        fields += s"copy=${p.copy.getOrElse("None")}"
        fields += s"copied=[${p.copied.mkString(",")}]"
        fields += s"wasrams=[${p.wasrams.mkString(",")}]"
      case p:OnChipMem =>
        fields += s"size=${p.size}"
        fields += s"RP=[${p.readPort.to.mkString(",")}], WP=${p.writePort.from}"
        fields += s"banking=${p.banking}, dblBuf=${p.buffering}"
        p match { case p:SRAMOnRead => fields += s"RA=${p.readAddr.from}"; case _ => }
        p match { case p:SRAMOnWrite => fields += s"WA=${p.writeAddr.from}, writeCtr=${p.writeCtr}"; case _ => }
        p match { case p:FIFOOnWrite => fields += s"start=${p.start}, end=${p.end}"; case _ => }
      case p:Stage =>
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
      case p:Reg => p match {
        case r:PipeReg =>
        case r:Const => fields += s"${r.value}"
        case r:ArgIn =>
        case r:ArgOut =>
      }
      case s:Scalar =>
        fields += s"writer=${s.writer.ctrler} readers=[${s.readers.map(_.ctrler).mkString(",")}]"
      case v:Vector =>
        fields += s"writer=${v.writer.ctrler} readers=[${v.readers.map(_.ctrler).mkString(",")}]" 
        v match {
          case n:DummyVector => fields += s"scalars=(${n.scalars.mkString(",")})" 
          case _ =>
        }
      case _ =>
    }
    s"(${if (fields.size>0) fields.reduce(_+", "+_) else ""})"
  }

}
