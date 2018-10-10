package spade
package node

import param._

import scala.collection.mutable

case class BundleGroup(
  param:Parameter, 
  coord:Option[(Int,Int)]=None
) {

  val bundleMap = mutable.Map[ClassTag[_], GridBundle[_<:PinType]]()
  def addBundle[B<:PinType:ClassTag](bundle:GridBundle[B]) = {
    bundleMap += implicitly[ClassTag[B]] -> bundle
  }
  def bundle[B<:PinType:ClassTag] = bundleMap(implicitly[ClassTag[B]]).asInstanceOf[GridBundle[B]]
  def bundles = bundleMap.values.toList

  def connect[B<:PinType:ClassTag](dst:BundleGroup, channelWidth:Int):Unit = {
    val outs = bundle[B].addOuts(channelWidth)
    val ins = dst.bundle[B].addIns(channelWidth)
    outs.zip(ins).foreach { case (o, i) => i <== o }
  }
}
