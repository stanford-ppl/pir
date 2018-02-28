package prism.node

import pirc._
import pirc.util._

import scala.reflect._
import scala.collection.mutable

trait Design extends IR {
  private var nextSym = 1
  def nextId = if (staging) {nextSym += 1; nextSym } else -1
  var staging = true
}
