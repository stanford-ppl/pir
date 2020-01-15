package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.codegen._

class PlastirouteLinkGen(implicit compiler: PIR) extends PlastisimUtil with PIRTraversal with ChildFirstTraversal with UnitTraversal { self =>
  lazy val outputFile = new CSVPrinter {
    setHeaders("out","ctx","src","tp","count")
  }
  lazy val inputFile = new CSVPrinter {
    setHeaders("in","dst")
  }
  lazy val linkFile = new CSVPrinter {
    setHeaders("out","in[0]","in[1]","in[2]")
  }

  override def finPass = {
    outputFile.writeToCSV(config.psimOut,config.prouteOutLinkName)
    inputFile.writeToCSV(config.psimOut,config.prouteInLinkName)
    linkFile.writeToCSV(config.psimOut,config.prouteLinkName)
    super.finPass
    //lnFile(buildPath(config.tstHome, "plasticine", "resources", "bin", "gen_link.py"), buildPath(config.psimOut, "gen_link.py"))
  }

  override def visitNode(n:N) = n match {
    case n:GlobalOutput => emitLink(n)
    case n => super.visitNode(n)
  }

  def quote(n:Any) = n match {
    case n:GlobalIO if n.isExtern.get => 
      val name = n.externAlias.v.getOrElse(s"$n")
      s"${config.externPrefix.getOrElse(s"/$topName")}/$name"
    case n:GlobalIO => 
      val name = n.externAlias.v.getOrElse(s"$n")
      s"${config.modulePrefix.getOrElse(s"/$topName")}/$name"
    case n:GlobalContainer if n.isExtern.get =>
      s"${config.externPrefix.getOrElse(s"/$topName")}/$n"
    case n:GlobalContainer =>
      s"${config.modulePrefix.getOrElse(s"/$topName")}/$n"
    case n => n.toString
  }

  def emitLink(n:GlobalOutput) = {
    // Emit output
    if (!n.isExtern.get) {
      val row = outputFile.newRow
      val ctx = n.in.T.ctx.get
      row("out") = quote(n)
      row("ctx") = ctx.id
      row("src") = quote(n.global.get)
      row("tp") = if (isVecLink(n)) 1 else 2 // 1 for scalar, 2 for vector
      row("count") = n.count.get.getOrElse(1000000) //TODO: use more reasonable heuristic when count is not available
    }

    n.out.T.view.foreach { case gin =>
      // Emit inputs
      if (!gin.isExtern.get) {
        val row = inputFile.newRow
        row(s"in") = quote(gin)
        row(s"dst") = quote(gin.global.get)
      }
    }

    // Emit connections
    val ins = if (n.isExtern.get) n.out.T.filter { !_.isExtern.get } else n.out.T
    if (ins.nonEmpty) {
      val row = linkFile.newRow
      row(s"out") = quote(n)
      ins.zipWithIndex.foreach { case (in, i) =>
        row(s"in[$i]") = quote(in)
      }
    }

  }

}
