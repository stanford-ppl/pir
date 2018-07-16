package pir

import spade.node._

package object node {
  implicit def dramAddress_to_access(dramAddr:DramAddress)(implicit design:PIRDesign) = {
    ReadMem(dramAddr)
  }
}
