package pir.codegen
import pir.node._

class PIRIRDotCodegen(val fileName:String)(implicit design:PIR) extends PIRCodegen with IRDotCodegen {

  import pirmeta._

  override def quote(n:Any):String = qtype(n) 

  override def label(attr:DotAttr, n:Any) = {
    var label = quote(n) 
    n match {
      case n:Primitive if isCounter(n) =>
        val fields = n.fieldNames.zip(n.productIterator.toList).flatMap { 
          case (field, Const(v)) => Some(s"$field=$v")
          case (field, v:Int) => Some(s"$field=$v")
          case _ => None
        }
        label += s"\n(${fields.mkString(",")})"
      case n:OpDef => 
        label += s"\n(${n.op})"
      case n:StreamIn => label += s"\n(${n.field})"
      case n:StreamOut => label +=s"\n(${n.field})"
      case n:GlobalInput => label += s"\n(from=${n.globalOutput})"
      case n:GlobalOutput => 
        label += s"\n(to=${n.out.connected.map(_.src).mkString(",\n")})"
      case n =>
    }
    n match {
      case n:PIRNode => 
        val metas = List(ctrlOf, topCtrlOf, boundOf, itersOf)
        metas.foreach { meta =>
          meta.asK(n).flatMap { k => meta.get(k) }.foreach { v =>
            label += s"\n(${meta.name}=$v)"
          }
        }
      case _ =>
    }
    attr.label(label)
  }

  //def shape(attr:DotAttr, n:Any) = attr.shape(box)

  override def color(attr:DotAttr, n:Any) = n match {
    case n:RetimingFIFO => attr.fillcolor(gold).style(filled)
    case n:Memory if isFIFO(n) => attr.fillcolor(gold).style(filled)
    case n:Memory if isReg(n) => attr.fillcolor(limegreen).style(filled)
    case n:Memory if isRemoteMem(n) => attr.fillcolor(chartreuse).style(filled)
    case n:ContextEnable => attr.fillcolor(orange).style(filled)
    case n:ContextEnableOut => attr.fillcolor(orange).style(filled)

    case n:ComputeContext => attr.fillcolor(palevioletred).style(filled)
    case n:Primitive if isCounter(n) => attr.fillcolor(indianred).style(filled)
    case n:CUContainer => attr.fillcolor(deepskyblue).style(filled)
    case n:DramFringe => attr.fillcolor("lightseagreen").style(filled)
    case n => super.color(attr, n)
  }

  def usedByCounter(n:PIRNode) = {
    n.collect[Primitive](visitFunc=n.visitGlobalOut, depth=2).filter(isCounter).nonEmpty
  }

  override def emitNode(n:N) = {
    n match {
      case n:Const[_] if usedByCounter(n) => super.visitNode(n)
      case n:High =>
      case n:Low =>
      case n:Primitive => emitSingleNode(n); super.visitNode(n)
      case n => super.emitNode(n) 
    }
  }

  def areLocal(a:N, b:N) = {
    val cuA = a.collectUp[GlobalContainer]().headOption
    val cuB = b.collectUp[GlobalContainer]().headOption
    cuA == cuB
  }

  override def emitEdge(from:N, to:N, attr:DotAttr) = {
    (from, to) match {
      case (from:ArgInDef, to) if !areLocal(from, to) =>
      case (from, to:ArgIn) if !areLocal(from, to) =>
      case (from:GlobalOutput, to:GlobalInput) =>
      case (from, to) => super.emitEdge(from, to, attr)
    }
  }

}
