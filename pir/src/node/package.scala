package pir

package object node {
  def isRemoteMem(n:Memory) = n match {
    case (_:SRAM | _:StreamIn | _:StreamOut)  => true
    case n:FIFO if n.writers.size > 1 => true
    case n:RegFile => true
    case _ => false
  }
}
