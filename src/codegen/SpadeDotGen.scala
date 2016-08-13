package pir.graph.traversal

import pir._
import pir.codegen.Printer
import pir.PIRMisc._
import pir.plasticine.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

object CUPrinter extends DotGen { override val stream = newStream(Config.spadeNetwork) }
object ArgPrinter extends DotGen { override val stream = newStream(Config.spadeArgInOut) }
object CtrPrinter extends DotGen { override val stream = newStream(Config.spadeCtr) }
class SpadeNetworkDot(implicit design: Design) extends Traversal {
  import DotEnum._

  override def initPass = {
    CUPrinter.emitBSln("digraph G")
    ArgPrinter.emitBSln("digraph G")
    CtrPrinter.emitBSln(s"digraph G")
    //CUPrinter.emitln(s"splines=ortho;")
  }

  override def traverse = {
    design.arch.cus.foreach { cu =>
      val recs = ListBuffer[String]()
      recs += s"{${cu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
      recs += s"${cu}"
      recs += s"<${cu.vout}> ${cu.vout}"
      val label = recs.mkString("|")
      CUPrinter.emitNode(cu, label, DotAttr().setShape(Mrecord))
      ArgPrinter.emitNode(cu, label, DotAttr().setShape(Mrecord))
      cu.vins.foreach { vin =>
        vin.fanIns.foreach { vout =>
          if (vout.src.isDefined) {
            CUPrinter.emitEdge(vout.src.get, vout, cu, vin)
          } else { // ArgIn
            ArgPrinter.emitEdge(s"""argin_${vout}""", s"""${cu}:${vin}:n""")
          }
        }
      }
    }
    design.arch.top.argOutBuses.foreach { vin =>
      vin.fanIns.foreach { vout =>
        ArgPrinter.emitEdge(s"""${vout.src.get}:${vout}:s""", s"""argout_${vin}""")
      }
    }

    design.arch.rcus.head.ctrs.foreach { ctr =>
      val recs = ListBuffer[String]()
      recs += s"<en> en"
      recs += s"${ctr}"
      recs += s"<sat> sat"
      val label = recs.mkString(s"|")
      CtrPrinter.emitNode(ctr, label, DotAttr().setShape(Mrecord))
      ctr.en.fanIns.foreach { from => 
        CtrPrinter.emitEdge(s"${s"${from}".replace(".", ":")}", 
                            s"${s"${ctr.en}".replace(".",":")}")
      }
    }
  }

  override def finPass = {
    CUPrinter.emitBEln
    CUPrinter.close
    ArgPrinter.emitBEln
    ArgPrinter.close
    CtrPrinter.emitBEln
    CtrPrinter.close
    info(s"Finishing Spade Dot Printing in ${CUPrinter.getPath} ${ArgPrinter.getPath} ${CtrPrinter.getPath}")
  }

}
