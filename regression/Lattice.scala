//import scala.reflect.runtime.universe._
//import scala.reflect.runtime.currentMirror
//import scala.tools.reflect.ToolBox
//import spatial.dsl.{spatial, SpatialApp}

//import lattice._

//case class LatticeParam(
  //iterations:scala.Int=1048576,
  //ts:scala.Int=32768,
  //op:scala.Int=1
//) extends Param[LatticeParam]

//class Lattice_0 extends Lattice
////class Lattice_1 extends Lattice { override lazy val param = LatticeParam(op=2) }
////class Lattice_2 extends Lattice { override lazy val param = LatticeParam(op=3) }

//@spatial abstract class Lattice extends DSETest {
  //import spatial.dsl._

  //lazy val param = LatticeParam()
  //import param._
  //val ip = 16

  //val sizes = scala.Array.fill(5)(2)
  //val size_prod:scala.Int = sizes.product
  //val dimensions:scala.Int = 16//sizes.length

  //type T = FixPt[FALSE, _16, _16]
  //type O = FixPt[TRUE, _16, _16]
  //override def main(args: Array[String]): Unit = {

    //val out = DRAM[O](iterations)
    //Accel {
      //val input_sram = LUT[T](dimensions)(scala.Seq.fill(dimensions)(0.to[T]):_*)
      //val param_sram = LUT[O](size_prod)(scala.Seq.fill(size_prod)(0.to[O]):_*)
      //val evaluator = LatticeProgram.constructSimplex(3, 13, 3, false, sizes)
      //val outSram = SRAM[O](ts)
      //Foreach(iterations by ts par op) { i =>
        //Foreach (ts par ip) { _ =>
          //outSram(i) = evaluator.uevaluate(
            //{ x: scala.Int => input_sram(I32(x)) },
            //{ x: I32 => param_sram(x) }
          //).asInstanceOf[O]
        //}
        //out(i::i+ts par ip) store outSram
      //}
    //}
    //val gold = loadCSV1D[T](s"${System.getProperty("user.dir")}/test_parameters/simplex/${dimensions}-${sizes(0)}/output.csv")
    //println(r"Wanted: ${gold(0)}")
    //assert(true)
  //}
//}

//object LatticeProgram {
  //val toolbox = currentMirror.mkToolBox()

  //val states = scala.collection.mutable.Map[String, argon.State]()


  //def toType(b: Boolean): Tree = {
    //if (b) tq"spatial.dsl.TRUE" else tq"spatial.dsl.FALSE"
  //}

  //def toType(i: Int): Tree = {
    //tq"spatial.dsl.${TypeName("_" + i)}"
  //}

  //def constructFixPtType(sign: Boolean, integer: Int, fraction: Int): Tree = {
    //tq"spatial.dsl.FixPt[${toType(sign)}, ${toType(integer)}, ${toType(fraction)}]"
  //}

  //def log2(x: Int): Int = {
    //((0 until x) find {1 << _ >= x}).get
  //}

  //def strides(dimensions: IndexedSeq[Int]): IndexedSeq[Int] = {
    //val strides: Array[Int] = Array.fill(dimensions.length){1}
    //(1 until dimensions.length) foreach {
      //d => {
        //strides(d) = strides(d-1) * dimensions(d-1)
      //}
    //}
    //strides
  //}

  //def constructSimplex(integer: Int, mantissa: Int, output: Int, sign: Boolean, dimensions: Array[Int])
                      //(implicit s: argon.State): SimplexLattice[_, _, _, _, _] = {
    //val totalSize = dimensions.product
    //val log2Size = log2(totalSize)
    //val simplexLatticeType =
      //tq"lattice.SimplexLattice[${toType(integer)}, ${toType(mantissa)}, ${toType(log2Size)}, ${toType(output)}, ${toType(sign)}]"



    //val arrayargs = (strides(dimensions) map {d => Literal(Constant(d))}).toList
    //val name = s.config.name
    //states += name -> s

    //val qq = q"""{
        //import spatial.dsl._
        //implicit val state: argon.State = LatticeProgram.states(${Literal(Constant(name))})
        //val n = new $simplexLatticeType(${Literal(Constant(dimensions.length))}, scala.Array(..$arrayargs))
        //n
        //}"""

    //toolbox.eval(qq).asInstanceOf[SimplexLattice[_, _, _, _, _]]
  //}

  //def constructHypercube(integer: Int, mantissa: Int, output: Int, sign: Boolean, dimensions: Array[Int])
                      //(implicit s: argon.State): HypercubeLattice[_, _, _, _, _] = {
    //val totalSize = dimensions.product
    //val log2Size = log2(totalSize)
    //val simplexLatticeType =
      //tq"lattice.HypercubeLattice[${toType(integer)}, ${toType(mantissa)}, ${toType(log2Size)}, ${toType(output)}, ${toType(sign)}]"



    //val arrayargs = (strides(dimensions) map {d => Literal(Constant(d))}).toList
    //val name = s.config.name
    //states += name -> s

    //val qq = q"""{
        //import spatial.dsl._
        //implicit val state: argon.State = LatticeProgram.states(${Literal(Constant(name))})
        //val n = new $simplexLatticeType(${Literal(Constant(dimensions.length))}, scala.Array(..$arrayargs))
        //n
        //}"""

    //toolbox.eval(qq).asInstanceOf[HypercubeLattice[_, _, _, _, _]]
  //}
//}

