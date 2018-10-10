package spade
package node

import param._
import scala.collection.mutable

abstract class Network[B<:PinType](
  param:NetworkParam[B], top:Top
)(implicit design:SpadeDesign) {
  implicit val bct = param.bct
  import param._
  import top._

  bundleGroups.foreach { node => node.addBundle(GridBundle[B]()) }

  def tpOf(node:BundleGroup) = node.param match {
    case param:PCUParam => "pcu"
    case param:PMUParam => "pmu"
    case param:SCUParam => "scu"
    case param:ArgFringeParam => "arg"
    case param:MCParam => "mc"
    case param:DramAGParam => "dag"
    case param:RouterParam => "rt"
    case param:SwitchParam => "sb"
  }

  object WithCoord {
    def unapply(n:BundleGroup) = n match {
      case BundleGroup(param, coord) => coord
    }
  }
  object WithParam {
    def unapply(n:BundleGroup) = n match {
      case BundleGroup(param, coord) => Some(param)
    }
  }

  def getChannelWidth(src:BundleGroup, dst:BundleGroup, srcDir:String, dstDir:String):Int = {
    channelWidth("src"->tpOf(src), "dst"->tpOf(dst), "srcDir"->srcDir, "dstDir"->dstDir)
  }

  def getChannelWidth(src:BundleGroup, dst:BundleGroup):Int = (param, src, dst) match {
    case (param, WithParam(_:RouterParam), WithParam(_:RouterParam)) => 1
    case (param:StaticGridNetworkParam[_], WithCoord(sx, sy), WithCoord(dx, dy)) if (sy < dy & sx == dx) => getChannelWidth(src, dst, "S", "N")
    case (param:StaticGridNetworkParam[_], WithCoord(sx, sy), WithCoord(dx, dy)) if (sy > dy & sx == dx) => getChannelWidth(src, dst, "N", "S")
    case (param:StaticGridNetworkParam[_], WithCoord(sx, sy), WithCoord(dx, dy)) if (sy == dy & sx < dx) => getChannelWidth(src, dst, "W", "E")
    case (param:StaticGridNetworkParam[_], WithCoord(sx, sy), WithCoord(dx, dy)) if (sy == dy & sx > dx) => getChannelWidth(src, dst, "E", "W")
    case (param:StaticGridNetworkParam[_], WithCoord(sx, sy), WithCoord(dx, dy)) if (sy < dy & sx < dx) => getChannelWidth(src, dst, "SW", "NE")
    case (param:StaticGridNetworkParam[_], WithCoord(sx, sy), WithCoord(dx, dy)) if (sy > dy & sx < dx) => getChannelWidth(src, dst, "NW", "SE")
    case (param:StaticGridNetworkParam[_], WithCoord(sx, sy), WithCoord(dx, dy)) if (sy < dy & sx > dx) => getChannelWidth(src, dst, "SE", "NW")
    case (param:StaticGridNetworkParam[_], WithCoord(sx, sy), WithCoord(dx, dy)) if (sy > dy & sx > dx) => getChannelWidth(src, dst, "NE", "SW")
    case (param, src, dst) => channelWidth("src"->tpOf(src), "dst"->tpOf(dst))
  }

  def connect(a:BundleGroup, b:BundleGroup):Unit = {
    a.connect[B](b, getChannelWidth(a, b))
    b.connect[B](a, getChannelWidth(b, a))
  }

}
