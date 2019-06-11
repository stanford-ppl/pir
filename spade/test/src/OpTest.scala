package spade
package test


import spade.param._
import prism.test._

class OpTest extends UnitTest { self =>

  "opTest" should "success" in {
    assert(FixAdd.eval(List(Literal(3),Literal(4))) == Some(Literal(7)))
    assert(FixAdd.eval(List(Literal(3.2),Literal(4))) == Some(Literal(7.2)))
    assert(FixAdd.eval(List(Literal(3.2f),Literal(4))) == Some(Literal(7.2f)))
    assert(FixFMA.eval(List(Literal(3.2f),Literal(4),Literal(5))) == Some(Literal(3.2f*4+5)))
    assert(FixOr.eval(List(Literal(0),"4")) == Some("4"))
    assert(FixOr.eval(List("x",Literal(0.0))) == Some("x"))

    {
      val a = Literal(List(1,2,3))
      val b = Literal(List(1,2,3))
      val c = Some(Literal(List(2,4,6)))
      assert(FixAdd.eval(List(a,b)) == c)
    }

    {
      val a = Literal(List(1,2,3))
      val b = "test" 
      val c = None
      assert(FixAdd.eval(List(a,b)) == c)
    }

    {
      val a = Literal(List(1,2,3))
      val b = Literal(4) 
      val c = Some(Literal(List(5,6,7))) 
      assert(FixAdd.eval(List(a,b)) == c)
    }
    {
      val a = Literal(List(0,0,0))
      val b = "test" 
      val c = Some(b) 
      assert(FixOr.eval(List(a,b)) == c)
    }
  }

}

