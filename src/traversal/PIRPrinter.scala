package pir.graph.traversal

import pir.graph._
import pir._
import pir.PIRMisc._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class PIRPrinter(implicit design: Design) extends DFSTraversal with Printer{

  override val stream = newStream(Config.pirFile) 

  override def initPass() = {
    super.initPass
  }

  def genFields(node:Node):String = {
    val fields = ListBuffer[String]()
    node match {
      case n:ComputeUnit =>
        fields += s"parent=${n.parent}"
        fields += s"type=${n.tpe}"
      case p:OffChip =>
        fields += s"RA=[${p.readAddrs.mkString(",")}], WA=[${p.writeAddrs.mkString(",")}]"
        fields += s"RC=[${p.readChannels.mkString(",")}], WC=[${p.writeChannels.mkString(",")}]"
      case n:Primitive => {
        //fields += s"ctrler=${n.ctrler}"
        n match {
          case p:CounterChain =>
            fields += s"copy=${p.copy.getOrElse("None")}"
          case p:SRAM =>
            fields += s"size=${p.size}, RA=${p.readAddr}, WA=${p.writeAddr}, RP=${p.writeAddr}"
          case p:Stage =>
            fields += s"operands=[${p.operands.mkString(",")}], op=${p.op}, result=${p.result}"
          case p:ScalarIn =>
            fields += s"scalar=${p.scalar}, writer=${p.scalar.writers.mkString(",")}"
          case p:ScalarOut =>
            fields += s"scalar=${p.scalar}, readers=[${p.scalar.readers.mkString(",")}]"
          case p:VecIn =>
            fields += s"vector=${p.vector}, writer=${p.vector.writers.mkString(",")}"
          case p:VecOut =>
            fields += s"vector=${p.vector}, readers=[${p.vector.readers.mkString(",")}]"
          case p:Counter => 
            fields += s"min=${p.min}, max=${p.max}, step=${p.step}"
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

  private def toStr(mp:Map[String, String], s:String, i:Int) = mp += (s -> i.toString)
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
    toStr(m, "stageUses" , c.stageUses  )
    toStr(m, "stageDefs" , c.stageDefs  )
    toStr(m, "stagePRs"  , c.stagePRs   )
    m
  }

  def emitBlock(title:String, node:Node):Unit = {
    emitBS(title)
    node match {
      case n:ComputeUnit =>
        emitBS(s"mapping=")
        regMapToStrs(n).foreach { case (k, v) =>
          emitln(s"${k}:${v}")
        }
        emitBE
      case _ =>
    }
    super.visitNode(node)
    emitBE
  }

  override def visitNode(node: Node) : Unit = {
    node match {
      case n:Controller => emitBlock(s"${node}${genFields(node)}", node)
      case n:CounterChain => emitBlock(s"${node}${genFields(node)}", node)
      case _ => emitln(s"${node}${genFields(node)}")
    }
  }

  override def finPass() = {
    info(s"Finishing PIR Printing in ${getPath}")
    close
  }
}
