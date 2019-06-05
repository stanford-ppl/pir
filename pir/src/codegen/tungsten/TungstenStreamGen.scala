package pir
package codegen

import pir.node._
import prism.graph._
import scala.collection.mutable

trait TungstenStreamGen extends TungstenCodegen with TungstenCtxGen {

  override def emitNode(n:N) = n match {
    case n@FringeStreamRead(bus@FileBus(filePath)) =>
      val file = s"${n}_file"
      genCtxFields {
        emitln(s"""ofstream $file;""")
      }
      genCtxInits {
        emitln(s"""$file.open("${filePath}", std::ios::out);""")
      }
      n.lastBit.T.foreach { last =>
        emitln(s"bool $last = false;")
      }
      emitBlock(s"for (int i=0; i < ${n.streams.head.getVec}; i++)") {
        val size = n.streams.size
        n.streams.zipWithIndex.foreach { case (stream, s) =>
          val dlim = if (s != size-1) s"""", ";""" else s"endl;"
          emitln(s"""$file << ${stream.qidx("i")} << $dlim""")
        }
        n.lastBit.T.foreach { last =>
          emitln(s"$last |= ${n.streams.last.qidx("i")};")
        }
      }
      n.lastBit.T.foreach { last =>
        last.as[BufferWrite].out.T.foreach { send =>
          addEscapeVar(send)
          genCtxInits {
            emitln(s"AddSend(${nameOf(send)});");
          }
          emitIf(s"$last") {
            emitln(s"""${nameOf(send)}->Push(make_token($last));""")
            emitln(s"$file.close();")
          }
        }
      }
      if (config.asModule && bus.withLastBit) {
        genCtxInits {
          emitln(s"Expect(1);")
        }
        emitIf(s"${n.streams.last.qidx("i")}") {
          emitln(s"Complete(1);")
        }
      }

    case n@FringeStreamWrite(FileBus(filePath)) =>
      val file = s"${n}_file"
      genCtxFields {
        emitln(s"""ifstream $file;""")
        emitln(s"bool eof = false;")
      }
      emitln(s"""if (!$file.is_open() & !eof) $file.open("${filePath}", std::ios::in);""")
      emitln(s"bool good = $file.good();")
      emitIfElse(s"good") {
        n.streams.foreach { stream =>
          emitln(s"${stream.qtp} ${stream.T}_vec[${stream.getVec}];")
        }
        emitln(s"bool validToken = false;")
        emitBlock(s"for (int i=0; i < ${n.streams.head.getVec}; i++)") {
          emitln(s"string line;")
          emitln(s"getline($file, line, '\\n');")
          emitln(s"if (!(good = $file.good())) break;")
          emitln(s"validToken = true;")
          emitln(s"istringstream lineStream(line);")
          emitln(s"string token;")
          n.streams.zipWithIndex.foreach { case (stream, s) =>
            emitln(s"getline(lineStream, token,',');")
            emitln(s"${stream.T}_vec[i] = parse<${stream.qtp}>(token);")
          }
        }
        n.streams.foreach { stream =>
          emitUnVec(stream.T)(s"${stream.T}_vec")
          stream.T.as[BufferWrite].out.T.foreach { send =>
            addEscapeVar(send)
            genCtxInits {
              emitln(s"AddSend(${nameOf(send)});");
            }
            emitIf("validToken") {
              emitln(s"""${nameOf(send)}->Push(make_token(${stream.T}));""")
            }
          }
        }
      } {
        emitln(s"InActive();")
        emitIf(s"eof = $file.eof()") {
          emitln(s"$file.close();")
        }
      }

    case n => super.emitNode(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:FringeDenseLoad => (s"DenseLoadAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n:FringeDenseStore => (s"DenseStoreAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n:FringeSparseLoad => (s"SparseLoadAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n:FringeSparseStore => (s"SparseStoreAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n => super.varOf(n)
  }

  override def quoteRef(n:Any):String = n match {
    case n@OutputField(x:DRAMCommand, field) => s"true"
    case n => super.quoteRef(n)
  }

}

