package pir.pass

import pir.node._

import prism.util.Cache

class PlastisimAnalyzer(implicit compiler: PIR) extends PIRTraversal with ChildFirstTraversal with UnitTraversal with ConstantPropogator {
  import pirmeta._

  type Link = LocalStore

  override def initPass = {
    super.initPass
    linkScaleInOf.clear
    linkScaleOutOf.clear
  }

  override def runPass =  {
    traverseNode(compiler.top)
  }

  override def visitNode(n:N) = n match {
    case n:Link => analyzeLink(n)
    case n => super.visitNode(n)
  }

  def analyzeLink(n:Link) = dbgblk(s"analyzeLink($n)") {
    // Analyze writer side
    linkScaleOutOf(n) = getItersOf(n)
    // Analyze reader side
    val mem::Nil = memsOf(n)
    mem match {
      case mem:ArgOut =>
        linkScaleInOf(n) = 1
      case mem:TokenOut =>
        linkScaleInOf(n) = 1
      case WithReaders(readers) =>
        val iters = readers.map(reader => getItersOf(reader))
        dbg(s"mem=${quote(mem)}")
        dbg(s"readers=${quote(readers)} iters=${iters}")
        assert(iters.toSet.size==1, s"readNext are different between readers of the same mem $mem")
        linkScaleInOf(n) = iters.head
    }
  }

  def getItersOf(n:Primitive):Int = itersOf.getOrElseUpdate(n) {
    dbgblk(s"computeItersOf(${quote(n)})") {
      n match {
        case Def(ctr:Counter, Counter(min, max, step, par)) =>
          val cmin = getConstOf[Int](min, logger=Some(this))
          val cmax = getConstOf[Int](max, logger=Some(this))
          val cstep = getConstOf[Int](step, logger=Some(this))
          dbg(s"ctr=${quote(ctr)} cmin=$cmin, cmax=$cmax, cstep=$cstep par=$par")
          if ((cmax - cmin) % (cstep * par) != 0)
            warn(s"(max=$cmax - min=$cmin) % (step=$cstep * par=$par) != 0 for ${quote(ctr)}")
          val iters = (cmax - cmin) / (cstep * par)
          val en = ctr.getEnable.get
          iters * getItersOf(en)
        case Def(n, CounterDone(ctr)) =>
          getItersOf(ctr)
        case Def(n,DataValid(gin)) => 
          val Def(gout,GlobalOutput(data, valid)) = goutOf(gin).get
          getItersOf(valid)
        case n:ContextEnable => 1
        case n:DramControllerDone =>
          val cuP = globalOf(n).get
          val size = cuP.collectDown[StreamOut]().filter { _.field == "size" }.head
          val csize = getConstOf[Int](size, logger=Some(this))
          csize / 4 / parOf(n).get
        case n:LocalAccess => getItersOf(accessNextOf(n))
      }
    }
  }

}
