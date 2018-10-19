package prism
package test

import util._


class PerformanceTest extends UnitTest {
  
  case class Raw(x:Int)

  implicit class Helper(raw:Raw) {
    def +(y:Int) = raw.x + y
  }

  "ImplicitClassTest" should "success" taggedAs(Slow) in {
    val iter = 1000000000
    val raw = Raw(3)
    var sum = 0
    tic
    (0 until iter).foreach { _ =>
      sum += raw.x + 6
    }
    toc(s"explicit", "ms")
    tic
    sum = 0
    (0 until iter).foreach { _ =>
      sum += raw + 6
    }
    toc(s"implicit", "ms")
  }

  import graph._
  def to_tuple5[T1,T2,T3,T4,T5](values:List[Any]):(T1,T2,T3,T4,T5) = {
    val a::b::c::d::e::_ = values
    (to[T1](a),to[T2](b),to[T3](c),to[T4](d),to[T5](e))
  }

  def to[T](x:Any) = x.asInstanceOf[T]

}
