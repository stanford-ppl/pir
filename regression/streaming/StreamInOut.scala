import spatial.dsl._
import spatial.lang.FileBus

@spatial class StreamInOut extends DSETest {
    def main(args: Array[String]): Unit = {
     val in  = StreamIn[Tup2[Int,Bit]](FileBus[Tup2[Int,Bit]]("in.csv"))
     val out = StreamOut[Tup2[Int,Bit]](FileBus[Tup2[Int,Bit]]("out.csv"))
     Accel {
       Stream(*){
         out := in.value
       }
     }
     assert(true)
   }
 }
