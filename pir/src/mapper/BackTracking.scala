package pir.mapper

import pir._
import pir.util.typealias._
import pir.codegen._

import spade._

import pirc._
import pirc.util._
import pirc.exceptions._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack
import scala.util.{Try, Success, Failure}
import java.lang.Thread

// Back tracking search with Look-ahead
trait BackTrackingSearch { self: Mapper =>

  def recRes[R,N,M](
    n:N,
    constrain:(N, R, M) => M,
    resFilter:List[R] => List[R], // triedRes => reses 
    finPass:M => M,
    map:M
  ):M = {
    def cons(n:N, r:R, m:M, es:List[E]) = constrain(n, r, m)
    def rf(triedRes:List[R], exceps:List[E]) = resFilter(triedRes) 
    recResWithExcept(n, cons _, rf _, finPass, map)
  }

  def recResWithExcept[R,N,M](
    n:N,
    constrain:(N, R, M, List[E]) => M,
    resFilter:(List[R], List[E]) => List[R], // triedRes, exceptions => reses 
    finPass:M => M,
    map:M
  ):M = {
    val exceps = ListBuffer[E]()
    val triedRes = ListBuffer[R]()
    def compRes = {
      Try (resFilter(triedRes.toList, exceps.toList)) match {
        case Success(rs) => rs
        case Failure(e:MappingException[_]) => exceps += e; Nil
        case Failure(e) => throw e
      }
    }
    var reses = compRes 
    while (reses.nonEmpty) {
      val res = reses.head
      Try { 
        finPass(constrain(n, res, map, exceps.toList))
      } match {
        case Success(m) => return m
        case Failure(e@NoSolFound(es, mp)) => 
          exceps ++= es
        case Failure(e@FailToMapNode(n, es, mp)) => 
          exceps ++= es
        case Failure(me:E) => 
          exceps += me // constrain failed
        case Failure(e) => throw e
      }
      triedRes += res
      reses = compRes
    }
    throw FailToMapNode(n, exceps.toList, map)
  }

  def bind[R,N,M](
    allRes:List[R], 
    allNodes:List[N], 
    initMap:M, 
    constrain:(N, R, M) => M,
    finPass: M => M
  ):M = {
    type MP = (M, List[R])
    // Add filter to exclude mappedRes and triedRes
    def rf(n:N, mp:MP, triedRes:List[R]) = {
      val (m, usedRes) = mp
      allRes.diff(usedRes).diff(triedRes)
    }
    // Add a list to map to keep track of mappedRes/usedRes
    def cons(n:N, r:R, mp:MP):MP = {
      val (m, urs) = mp
      (constrain(n, r, m), urs :+ r) 
    }
    val (map,_) = 
      bind[R,N,MP](allNodes, (initMap, Nil), cons _, rf _, (mp:MP) => (finPass(mp._1),Nil))
    map
  }

  def bind[R,N,M](
    allNodes:List[N],
    initMap:M, 
    constrain:(N, R, M) => M,
    resFunc:(N,M,List[R]) => List[R], //(n, m, triedRes) => List[R]
    finPass: M => M
  ):M = {
    val total = allNodes.size
    /* Recursively map a list of nodes to a list of resource */
    def recNode(remainNodes:List[N], map:M):M = {
      if (remainNodes.size==0) { //Successfully mapped all nodes
        return finPass(map) // throw MappingException
      }
      val exceps = ListBuffer[E]()
      for (in <- 0 until remainNodes.size) { 
        val (h, n::rt) = remainNodes.splitAt(in)
        val restNodes = h ++ rt
        log (s"Mapping $n (${total-restNodes.size}/${total})", 
          finPass = { (m:M) => return m },
          failPass = { 
            case FailToMapNode(n, es, mp) => exceps ++= es
            case e => throw e // Unknown exception
          }
        ) { // Block
          def rn(m:M): M = recNode(restNodes, m)
          def rf(trs:List[R]):List[R] = resFunc(n, map, trs)
          recRes[R,N,M](n=n, constrain=constrain, resFilter=rf _, finPass=rn _, map=map)
        } 
      }
      throw NoSolFound(exceps.toList, map) 
    }

    recNode(allNodes, initMap)
  }

}
