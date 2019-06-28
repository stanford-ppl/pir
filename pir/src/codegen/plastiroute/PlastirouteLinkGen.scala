package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.codegen._

class PlastirouteLinkGen(implicit compiler: PIR) extends PlastisimCodegen with CSVCodegen { self =>
  override def fileName = config.prouteLinkName

  lazy val externLink = new PlastisimCodegen with CSVCodegen{
    override def dirName = self.dirName
    override def fileName = "extlink.csv"
  }

  override def runPass = {
    setHeaders("link","ctx","src","tp","count","dst[0]","out[0]")
    super.runPass
  }

  override def finPass = {
    if (externLink.rows.nonEmpty) {
      externLink.initPass
      externLink.finPass
    }
    super.finPass
    lnFile(buildPath(config.tstHome, "plasticine", "resources", "bin", "gen_link.py"), buildPath(config.psimOut, "gen_link.py"))
  }

  override def emitNode(n:N) = n match {
    case n:GlobalOutput => emitLink(n)
    case n => visitNode(n)
  }

  override def quote(n:Any) = n match {
    case n:GlobalIO if n.isExtern.get => 
      val name = n.externAlias.v.getOrElse(s"$n")
      s"/$name"
    case n:GlobalIO => 
      val name = n.externAlias.v.getOrElse(s"$n")
      s"${config.modulePreix.getOrElse("")}/$topName/$name"
    case n:GlobalContainer if n.isExtern.get =>
      s"/$n"
    case n:GlobalContainer =>
      s"${config.modulePreix.getOrElse("")}/$topName/$n"
    case _ => super.quote(n)
  }

  def emitLink(n:GlobalOutput) = {
    // Emit link src
    val row = if (n.isExtern.get) externLink.newRow else newRow
    val ctx = n.in.T.ctx.get
    row("link") = quote(n)
    row("ctx") = ctx.id
    row("src") = quote(n.global.get)
    row("tp") = if (n.getVec == 1) 1 else 2 // 1 for scalar, 2 for vector
    //row("count") = n.constCount
    row("count") = n.count.get.getOrElse(1000000) //TODO: use more reasonable heuristic when count is not available

    // Emit local dsts
    val localIns = n.out.T.filter { _.isExtern.get == n.isExtern.get }
    localIns.zipWithIndex.foreach { case (gin, idx) =>
      if (!gin.isExtern.get) {
        row(s"dst[$idx]") = quote(gin.global.get)
        row(s"out[$idx]") = quote(gin)
      }
    }

    // Emit bridging dsts
    val bridgeIns = n.out.T.filter { _.isExtern.get != n.isExtern.get }
    if (bridgeIns.nonEmpty) {
      val row = if (!n.isExtern.get) externLink.newRow else newRow
      row("link") = quote(n)
      bridgeIns.zipWithIndex.foreach { case (gin, idx) =>
        row(s"dst[$idx]") = quote(gin.global.get)
        row(s"out[$idx]") = quote(gin)
      }
    }

  }

}
