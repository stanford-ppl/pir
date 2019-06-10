package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.codegen._

class PlastirouteLinkGen(implicit compiler: PIR) extends PlastisimCodegen with CSVCodegen {
  override def fileName = config.prouteLinkName

  lazy val script = "gen_link.py"

  def genScript(block: => Unit) = enterFile(config.psimOut, script)(block)

  override def runPass = {
    if (!noPlaceAndRoute) {
      genScript {
        emitln("""
import argparse

parser = argparse.ArgumentParser(description='Generate full link.csv based on partial csv')
parser.add_argument('-p', '--partial', type=str, help='Path to partial csv')
parser.add_argument('-d', '--dst', type=str, help='Path to destination csv to append to')

(opts, args) = parser.parse_known_args()
parsed = {}
with open(opts.partial, 'r') as f:
    f.readline()
    for line in f:
        line = line.strip()
        link,rest = line.split(",", 1)
        parsed[link.split("/")[-1]] = line
with open (opts.dst, 'a') as f:
""")
      }
      setHeaders("link","ctx","src","tp","count","dst[0]","out[0]")
      super.runPass
      genScript {
emitln("""
    for link in parsed:
        f.write(parsed[link] + '\n')
""")
      }
      getStream(buildPath(config.psimOut, script)).get.close
    }
  }

  override def emitNode(n:N) = n match {
    case n:GlobalOutput => emitLink(n)
    case n => visitNode(n)
  }

  override def quote(n:Any) = n match {
    case n:GlobalIO if n.isExtern.get => 
      s"$name"
    case n:GlobalIO => 
      val name = n.externAlias.v.getOrElse(s"$n")
      s"${config.modulePreix.getOrElse("")}/$topName/$name"
    case n:GlobalContainer =>
      s"${config.modulePreix.getOrElse("")}/$topName/$n"
    case _ => super.quote(n)
  }

  def emitLink(n:GlobalOutput) = {
    val intInputs = n.out.T.filterNot { _.isExtern.get }
    val intIns = intInputs.map{ in => s"${quote(in.global.get)},${quote(in)}" }.mkString(",")
    val ctx = n.in.T.ctx.get
    if (n.isExtern.get) {
      // Inputs to PIR module.
      if (intInputs.nonEmpty) {
        genScript {
          emitln(s"""    f.write(parsed["${n.externAlias.get}"] + ",$intIns\\n") # external output""")
          emitln(s"""    del parsed["${n.externAlias.get}"]""")
        }
      }
    } else {
      if (n.out.T.exists { _.isExtern.get}) {
        genScript {
          val fields = List(
            quote(n),
            ctx.id,
            quote(n.global.get),
            if (n.getVec == 1) 1 else 2,
            1000000,
            intIns
          )
          emitln(s"""    f.write("${fields.mkString(",")}" + ",".join(parsed["${n.externAlias.get}"].split(",")[::-1]) + "\\n") # internal output""")
          emitln(s"""    del parsed["${n.externAlias.get}"]""")
        }
      } else {
        val row = newRow
        row("link") = quote(n)
        row("ctx") = ctx.id
        row("src") = quote(n.global.get)
        row("tp") = if (n.getVec == 1) 1 else 2 // 1 for scalar, 2 for vector
        //row("count") = n.constCount
        row("count") = n.count.get.getOrElse(1000000) //TODO: use more reasonable heuristic when count is not available
        n.out.T.zipWithIndex.foreach { case (gin, idx) =>
          if (!gin.isExtern.get) {
            row(s"dst[$idx]") = quote(gin.global.get)
            row(s"out[$idx]") = quote(gin)
          }
        }
      }
    }
  }
}
