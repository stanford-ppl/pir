package pir
package codegen

import pir.node._
import prism.graph._
import scala.collection.mutable

trait TungstenStreamGen extends TungstenCodegen with TungstenCtxGen with TungstenMemGen {

  override def emitNode(n:N) = n match {
    case n@FringeStreamRead(bus@FileBus(filePath)) =>
      val file = s"${n}_file"
      genCtxFields {
        emitln(s"""ofstream $file;""")
      }
      genCtxInits {
        emitln(s"""$file.open("${filePath}", std::ios::out);""")
      }
      emitln(s"bool last = false;")
      emitBlock(s"for (int i=0; i < ${n.streams.head.getVec}; i++)") {
        val size = n.streams.size
        n.streams.view.zipWithIndex.foreach { case (stream, s) =>
          val dlim = if (s != size-1) s"""", ";""" else s"endl;"
          emitln(s"""$file << ${stream.qidx("i")} << $dlim""")
        }
        if (n.lastBit.T.nonEmpty) {
          emitln(s"last |= ${n.streams.last.qidx("i")};")
        }
      }
      n.lastBit.T.foreach { last =>
        val ctrler = getCtrler(n)
        val ctrlerEn = s"$ctrler->Enabled()"
        last.as[BufferWrite].out.T.foreach { send =>
          addEscapeVar(send)
          genCtxInits {
            emitln(s"$ctrler->AddOutput(${nameOf(send)});");
          }
          emitIf(s"last & $ctrlerEn") {
            emitln(s"""${nameOf(send)}->Push(make_token(true));""")
            emitln(s"$file.close();")
          }
        }
      }
      if (config.asModule && bus.withLastBit) {
        genCtxInits {
          emitln(s"Expect(1);")
        }
        emitIf(s"last") {
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
          n.streams.view.zipWithIndex.foreach { case (stream, s) =>
            emitln(s"getline(lineStream, token,',');")
            emitln(s"${stream.T}_vec[i] = parse<${stream.qtp}>(token);")
          }
        }
        n.streams.foreach { stream =>
          emitUnVec(stream.T)(s"${stream.T}_vec")
          val ctrler = getCtrler(n)
          val ctrlerEn = s"$ctrler->Enabled()"
          stream.T.as[BufferWrite].out.T.foreach { send =>
            addEscapeVar(send)
            genCtxInits {
              emitln(s"$ctrler->AddOutput(${nameOf(send)});");
            }
            emitIf(s"validToken & $ctrlerEn") {
              emitln(s"""${nameOf(send)}->Push(make_token(${stream.T}));""")
            }
          }
        }
      } {
        emitIf(s"eof = $file.eof()") {
          emitln(s"$file.close();")
        }
      }

    case WithData(n:BufferWrite, data:StreamCommand) =>

    case n => super.emitNode(n)
  }

}

