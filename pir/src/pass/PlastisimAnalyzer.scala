package pir.pass

import pir.node._

import prism.util.Memorization

class PlastisimAnalyzer(implicit compiler: PIR) extends PIRTraversal with ChildFirstTraversal with UnitTraversal with ConstantPropogator with Memorization {
  import pirmeta._

  type Link = LocalStore

  override def reset = {
    resetAllCaches
    linkScaleInOf.clear
    linkScaleOutOf.clear
  }

  def shouldRun = spade.node.isMesh(compiler.arch.top) && spade.node.isDynamic(compiler.arch.top)

  override def runPass =  {
    traverseNode(compiler.top)
  }

  override def visitNode(n:N) = n match {
    case n:Link => analyzeLink(n)
    case n => super.visitNode(n)
  }

  def analyzeLink(n:Link) = {
    // Analyze writer side
    val Def(store, EnabledStoreMem(mem, addrs, data, writeNext)) = n
    linkScaleOutOf(store) = itersOf(writeNext)
    // Analyze reader side
    mem match {
      case mem:StreamOut =>
        val cuP = globalOf(mem).get
        val size = cuP.collectDown[StreamOut]().filter { _.field == "size" }.head
        val data = cuP.collectDown[StreamIn]().filter { _.field == "data" }.head
        val csize = getConstOf[Int](size)
        linkScaleInOf(store) = csize / 4 / parOf(data).get // size in bytes to words
      case mem:ArgOut =>
        linkScaleInOf(store) = 1
      case WithReader(Def(reader, EnabledLoadMem(mem, addrs, readNext))) => // enabledMem should only have one reader
        linkScaleInOf(store) = itersOf(readNext)
    }
  }

  val itersOf = Cache[Def, Int](computeIters)
  def computeIters(n:Def):Int = dbgblk(s"computeIters($n)") {
    n match {
      case Def(n, CounterDone(Def(ctr, EnabledCounter(min, max, step, par, en)))) =>
        val cmin = getConstOf[Int](min, logger=Some(this))
        val cmax = getConstOf[Int](max)
        val cstep = getConstOf[Int](step)
        dbg(s"ctr=$ctr cmin=$cmin, cmax=$cmax, cstep=$cstep par=$par")
        val iters = (cmax - cmin) / (cstep * par)
        iters * itersOf(en)
      case Def(n,DataValid(gin)) => 
        val Def(gout,GlobalOutput(data, valid)) = goutOf(gin).get
        itersOf(valid)
      case n:ContextEnable => 1
      case n:ArgInValid => 1
    }
  }

}
