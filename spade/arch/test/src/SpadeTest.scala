package arch.test

import arch._

import spade._
import spade.node._
import spade.util._
import spade.codegen._

import prism.util._
import prism._

import prism.test._

import org.scalatest._
import scala.language.reflectiveCalls
import scala.util.Random
import scala.collection.mutable.ListBuffer
import sys.process._
import scala.language.postfixOps

class SpadeTest extends UnitTest { self =>

  //"SpadeTest" should "success" in {
    //implicit val arch = SN2x2
    //arch.top.connect
    ////arch = SN16x8_LD
    ////arch = new SN(numRows=2, numCols=2, pattern=HalfHalf)
    //val cu = arch.pcus.head
    //info(s"${arch} ${quote(cu)}.vin=${cu.vins.size}")
    //info(s"${arch} ${quote(cu)}.vout=${cu.vouts.size}")
    //info(s"${arch} ${quote(cu)}.cin=${cu.cins.size}")
    //info(s"${arch} ${quote(cu)}.cout=${cu.couts.size}")
    //info(s"${arch} ${quote(cu)}.sin=${cu.sins.size}")
    //info(s"${arch} ${quote(cu)}.sout=${cu.souts.size}")
    //info(s"${arch} ${quote(cu)}.stages=${cu.stages.size}")
    //info(s"numLanes=${arch.numLanes}")
    //info(s"wordWidth=${arch.wordWidth}")
    //emitBlock("regs") {
      //cu.stages.headOption.foreach { _.prs.map(_.reg).foreach { reg =>
        //info(s"reg=${quote(reg)} colors=[${reg.colors.mkString(",")}]")
      //} }
    //}
    //new PlasticineCtrlDotPrinter().print
    //s"out/bin/run -cp out/${arch}/CtrlNetwork".replace(".dot", "") !

    //new PlasticineScalarDotPrinter().print
    //s"out/bin/run -cp out/${arch}/ScalNetwork".replace(".dot", "") !

    //new PlasticineVectorDotPrinter().print
    //s"out/bin/run -cp out/${arch}/VecNetwork".replace(".dot", "") !

    ////new PlasticinePrinter().run //this prints architecture in detail but is slow
    ////new PlasticineNetworkCodegen().run
    ////new PlasticineParamCodegen().run
    //arch.sbs.foreach { sb => 
      //(sb.vectorIO.ins ++ sb.scalarIO.ins ++ sb.ctrlIO.ins).foreach { in => 
        //if (in.fanIns.size>1) { 
          //println(s"Switchbox $sb has $in with fanIns > 1 ${in.fanIns}")
          //throw PIRException(s"Switchbox $sb has $in with fanIns > 1 ${in.fanIns}")
        //}
      //}
      //(sb.vectorIO.outs ++ sb.scalarIO.outs ++ sb.ctrlIO.outs).foreach { out => 
        //if (out.fanOuts.filterNot{_.src.isInstanceOf[Top]}.size>1) {
          //throw PIRException(s"Switchbox $sb has $out with fanOuts > 1 ${out.fanOuts}")
        //}
      //}
    //}
    //arch.cus.foreach { pcu =>
      //(pcu.vectorIO.ins ++ pcu.scalarIO.ins ++ pcu.ctrlIO.ins).foreach { in => 
        //if (in.fanIns.size>1) 
          //throw PIRException(s"ComputeUnit $pcu has $in with fanIns > 1 ${in.fanIns}")
      //}
      //pcu.ctrlIO.outs.foreach { out => 
        //if (out.fanOuts.size>1) 
          //throw PIRException(s"ComputeUnit $pcu has $out with fanOuts > 1 ${out.fanOuts}")
      //}
    //}
  //}

}

