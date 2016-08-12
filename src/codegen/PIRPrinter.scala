package pir.graph.traversal

import pir.graph._
import pir._
import pir.codegen.Printer
import pir.PIRMisc._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File

class PIRPrinter(implicit design: Design) extends DFSTraversal with Printer{

  override val stream:OutputStream = newStream(Config.pirFile) 

  override def initPass() = {
    super.initPass
  }

  def genFields(node:Node):String = {
    val fields = ListBuffer[String]()
    node match {
      case n:Controller =>
        fields += s"children=[${n.children.mkString(",")}]"
      case _ =>
    }
    node match {
      case n:ComputeUnit =>
        fields += s"parent=${n.parent}"
        fields += s"type=${n.tpe}"
        fields += s"dep=[${n.dependencies.mkString(",")}]"
        fields += s"deped=[${n.dependeds.mkString(",")}]"
        if (n.tpe==Pipe)
          fields += s"outers=[${n.outers.mkString(s",")}]"
        n match {
          case c:TileTransfer =>
            fields += s"mctpe=${c.mctpe}"
          case _ =>
        }
      case p:MemoryController =>
        fields += s"mctpe=${p.mctpe}"
      case n:Primitive => {
        //fields += s"ctrler=${n.ctrler}"
        n match {
          case p:CounterChain =>
            fields += s"copy=${p.copy.getOrElse("None")}"
          case p:SRAM =>
            fields += s"size=${p.size}, RA=${p.readAddr.from}, WA=${p.writeAddr.from}"
            fields += s"RP=[${p.readPort.to.mkString(",")}], WP=${p.writePort.from}"
            fields += s"cchain=${p.cchain}"
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
          case p:ScalarOut =>
            fields += s"scalar=${p.scalar}, readers=[${p.scalar.readers.mkString(",")}]"
          case p:VecIn =>
            fields += s"vector=${p.vector}, writer=${p.vector.writer}"
          case p:VecOut =>
            fields += s"vector=${p.vector}, readers=[${p.vector.readers.mkString(",")}]"
          case p:Counter => 
            fields += s"min=${p.min.from}, max=${p.max.from}, step=${p.step.from}, dep=${p.dep}"
          case p:LUT => 
            fields += s"init=${p.init}"
          case p:Reg => p match {
            case r:PipeReg =>
            case r:Const => fields += s"${r.value}"
            case r:ArgIn =>
            case r:ArgOut =>
          }
          case _ =>
        }
      }
      case _ =>
    }
    s"(${if (fields.size>0) fields.reduce(_+", "+_) else ""})"
  }

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
  def regMapToStrs(c:ComputeUnit):Map[String, String] = {
    var m = HashMap[String, String]()
    toStr(m, "reduceReg" , c.reduceReg  )
    toStr(m, "vecIns"    , c.vecIns      )
    toStr(m, "vecOut"    , c.vecOut     )
    toStr(m, "scalarIns" , c.scalarIns  )
    toStr(m, "scalarOuts", c.scalarOuts )
    toStr(m, "loadRegs"  , c.loadRegs   )
    toStr(m, "storeRegs" , c.storeRegs  )
    toStr(m, "ctrRegs"   , c.ctrRegs    )
    toStr(m, "tempRegs"  , c.tempRegs   )
    m
  }

  def emitBlock(title:String, node:Node):Unit = {
    emitBlock(title) {
      node match {
        case n:ComputeUnit =>
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
      case n:Stage => emitBlock(s"${node}${genFields(node)}", node)
      case _ => emitln(s"${node}${genFields(node)}")
    }
  }

  override def finPass() = {
    info(s"Finishing PIR Printing in ${getPath}")
    close
  }
}
