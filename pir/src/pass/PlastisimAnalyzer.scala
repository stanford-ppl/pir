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

  def shouldRun = spade.node.isMesh(compiler.arch.top)

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
      case mem:StreamOut if mem.field == "size" || mem.field == "offset" =>
        val cuP = globalOf(mem).get
        val size = cuP.collectDown[StreamOut]().filter { _.field == "size" }.head
        val data = (cuP.collectDown[StreamIn]().filter { _.field == "data" } ++
                    cuP.collectDown[StreamOut]().filter { _.field == "data" }).head
        val csize = getConstOf[Int](size)
        linkScaleInOf(store) = csize / 4 / parOf(data).get // size in bytes to words
      case mem:StreamOut if mem.field == "data" =>
        linkScaleInOf(store) = 1
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
    dbgblk(s"computeIters($n)") {
      n match {
        case Def(ctr:Counter, Counter(min, max, step, par)) =>
          val cmin = getConstOf[Int](min, logger=Some(this))
          val cmax = getConstOf[Int](max)
          val cstep = getConstOf[Int](step)
          dbg(s"ctr=$ctr cmin=$cmin, cmax=$cmax, cstep=$cstep par=$par")
          val iters = (cmax - cmin) / (cstep * par)
          val en = ctr.getEnable.get
          iters * itersOf(en)
        case Def(n, CounterDone(ctr)) =>
          itersOf(ctr)
        case Def(n,DataValid(gin)) => 
          val Def(gout,GlobalOutput(data, valid)) = goutOf(gin).get
          itersOf(valid)
        case n:ContextEnable => 1
        case n:ArgInValid => 1
      }
    }
  }

}
