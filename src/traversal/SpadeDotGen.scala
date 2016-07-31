package pir.graph.traversal

import pir._
import pir.PIRMisc._
import pir.plasticine.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

object CUPrinter extends Printer { override val stream = newStream(Config.spadeNetwork) }
object ArgPrinter extends Printer { override val stream = newStream(Config.spadeArgInOut) }
object CtrPrinter extends Printer { override val stream = newStream(Config.spadeCtr) }
class SpadeNetworkDot(implicit design: Design) extends Traversal with Printer {

  override def initPass = {
    CUPrinter.emitBS("digraph G")
    ArgPrinter.emitBS("digraph G")
    CtrPrinter.emitBS(s"digraph G")
    //CUPrinter.emitln(s"splines=ortho;")
  }
  override def traverse = {
    design.arch.cus.foreach { cu =>
      val recs = ListBuffer[String]()
      recs += s"{${cu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
      recs += s"${cu}"
      recs += s"<${cu.vout}> ${cu.vout}"
      CUPrinter.emitln(s"""${cu} [label="{${recs.mkString("|")}}", shape=Mrecord ];""")
      ArgPrinter.emitln(s"""${cu} [label="{${recs.mkString("|")}}", shape=Mrecord ];""")
      cu.vins.foreach { vin =>
        vin.mapping.foreach { vout =>
          if (vout.src.isDefined) {
            CUPrinter.emitln(s"""${vout.src.get}:${vout}:s -> ${cu}:${vin}:n""")
          } else { // ArgIn
            ArgPrinter.emitln(s"""argin_${vout} -> ${cu}:${vin}:n""")
          }
        }
      }
    }
    design.arch.top.argOutBuses.foreach { vin =>
      vin.mapping.foreach { vout =>
        ArgPrinter.emitln(s"""${vout.src.get}:${vout}:s -> argout_${vin}""")
      }
    }

    design.arch.rcus.head.ctrs.foreach { ctr =>
      val recs = ListBuffer[String]()
      recs += s"<en> en"
      recs += s"${ctr}"
      recs += s"<sat> sat"
      CtrPrinter.emitln(s"""${ctr} [label="${recs.mkString(s"|")}", shape=Mrecord ];""")
      ctr.en.mapping.foreach { from => 
        CtrPrinter.emitln(s"${s"$from".replace(".", ":")} -> ${s"${ctr.en}".replace(".",":")}")
      }
    }
  }

  override def finPass = {
    CUPrinter.emitBE
    CUPrinter.close
    ArgPrinter.emitBE
    ArgPrinter.close
    CtrPrinter.emitBE
    CtrPrinter.close
    info(s"Finishing Spade Config Printing in ${getPath}")
  }

}
