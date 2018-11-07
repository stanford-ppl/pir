package pir
package codegen

import pir.node._
import scala.collection.mutable

trait TungstenDRAMGen extends TungstenCodegen {

  override def emitNode(n:N) = n match {
    case n:FringeDenseLoad =>
      val size = n.size.T
      val offset = n.offset.T
      val data = n.data.T
      genFields {
        emitln(s"""DRAMDenseLoad<${data.getVec}> *$n;""")
      }
      genInits {
        emitln(s"""$n = new DRAMDenseLoad<${data.getVec}>("$n", "${n.dram.sid}.txt", fifo_$size, fifo_$offset, fifo_$data);""")
        emitln(s"addChild($n);")
      }

    case n:FringeDenseStore =>
      val size = n.size.T
      val offset = n.offset.T
      val data = n.data.T
      val valid = n.data.T
      val ack = n.data.T
      genFields {
        emitln(s"""DRAMDenseStore<${data.getVec}> *$n;""")
      }
      genInits {
        emitln(s"""$n = new DRAMDenseStore<${data.getVec}>("$n", "${n.dram.sid}.txt", fifo_$size, fifo_$offset, fifo_$data, fifo_$valid, fifo_$ack);""")
        emitln(s"addChild($n);")
      }

    case n:FringeSparseLoad =>
      val addr = n.addr.T
      val data = n.data.T
      genFields {
        emitln(s"""DRAMSparseStore *$n;""")
      }
      genInits {
        emitln(s"""$n = new DRAMSparseStore("$n", "${n.dram.sid}.txt", fifo_$addr, fifo_$data);""")
        emitln(s"addChild($n);")
      }

    case n:FringeSparseStore =>
      val addr = n.addr.T
      val data = n.data.T
      val ack = n.ack.T
      genFields {
        emitln(s"""DRAMSparseStore *$n;""")
      }
      genInits {
        emitln(s"""$n = new DRAMSparseStore("$n", "${n.dram.sid}.txt", fifo_$addr, fifo_$data, fifo_$ack);""")
        emitln(s"addChild($n);")
      }

    case n => super.emitNode(n)
  }

}
