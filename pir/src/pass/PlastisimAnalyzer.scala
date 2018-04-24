package pir.pass

import pir.node._

import prism.util.Cache
import prism.util.Memorization

class PlastisimAnalyzer(implicit compiler: PIR) extends PIRTraversal with ChildFirstTraversal with UnitTraversal with ConstantPropogator with Memorization {
  import pirmeta._

  type Link = LocalStore

  override def initPass = {
    super.initPass
    resetAllCaches
    memorizing = true
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
    val Def(store, EnabledStoreMem(mem, addrs, data, writeNext)) = n
    linkScaleOutOf(store) = itersOf(writeNext)
    // Analyze reader side
    mem match {
      case mem:ArgOut =>
        linkScaleInOf(store) = 1
      case mem:TokenOut =>
        linkScaleInOf(store) = 1
      case WithReaders(readers) =>
        val readNexts = readers.map {
          case Def(reader, EnabledLoadMem(mem, addrs, readNext)) =>
            readNext
        }.toSet
        dbg(s"mem=${quote(mem)}")
        dbg(s"readers=${quote(readers)}")
        dbg(s"readNexts=${quote(readNexts)}")
        assert(readNexts.size==1, s"readNext are different between readers of the same mem $mem")
        linkScaleInOf(store) = itersOf(readNexts.head)
    }
  }

  val itersOf:Cache[Primitive, Int] = memorize { case (n:Primitive) =>
    dbgblk(s"itersOf(${quote(n)})") {
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
          iters * itersOf(en)
        case Def(n, CounterDone(ctr)) =>
          itersOf(ctr)
        case Def(n,DataValid(gin)) => 
          val Def(gout,GlobalOutput(data, valid)) = goutOf(gin).get
          itersOf(valid)
        case n:ContextEnable => 1
        case n:DramControllerDone =>
          val cuP = globalOf(n).get
          val size = cuP.collectDown[StreamOut]().filter { _.field == "size" }.head
          val csize = getConstOf[Int](size, logger=Some(this))
          csize / 4 / parOf(n).get
      }
    }
  }

}
