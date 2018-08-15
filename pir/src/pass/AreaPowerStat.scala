package pir
package codegen

import pir.node._
import pir.pass._
import pir.mapper._
import spade.node._
import spade.param._

import prism.codegen.JsonCodegen

class AreaPowerStat(implicit compiler:PIR) extends PIRCodegen with prism.codegen.PythonCodegen {
  import pirmeta._
  import PIRConfig._

  val fileName = "area_power_model.py"

  override def runPass:Unit =  {
    if (isStatic(designS) || isDynamic(designS)) genScript
  }

  override def finPass = {
    super.finPass
    if (isStatic(designS) || isDynamic(designS)) {
      shellProcess("pir", s"python $outputPath", s"${buildPath(dirName, "area_power.log")}") { line =>
        if (PIRConfig.printStat) {
          if (line.contains("total_area")) info(Console.GREEN, s"pir", line)
          if (line.contains("total_net_energy")) info(Console.GREEN, s"pir", line)
          if (line.contains("total_net_power")) info(Console.GREEN, s"pir", line)
        }
        if (line.contains("Cannot find kws in the table")) {
          val kws = line.split("kws:")(1)
          fail(s"No characterized data for ${kws}")
        }
        dbg(line)
      }
    }
  }

  def format(a:Any) = a match {
    case a:String => s""""$a""""
    case a => a.toString
  }

  def genScript = {
    emitln(s"conf = {}")
    List("vlink", "slink", "net", "link-prop", "flit-width").foreach { key =>
      emitln(s"conf['$key']=${format(SpadeConfig.option[Any](key))}")
    }
    emitln(s"conf['max_vc']=${format(SpadeConfig.option[Any]("vc"))}")
    List("psim-q","proute-q").foreach { key =>
      emitln(s"conf['$key']=${format(option[Any](key))}")
    }
    val cellsS = compiler.arch.top.collectDown[Routable]()
    val pcusS = cellsS.filter { _.param.isInstanceOf[PCUParam] }
    val pmusS = cellsS.filter { _.param.isInstanceOf[PMUParam] }
    val row = SpadeConfig.option[Int]("row")
    val col = SpadeConfig.option[Int]("col")
    val nRouter = if (isDynamic(compiler.arch.top)) row * (col + 2) else 0
    val nSwitch = if (isDynamic(compiler.arch.top)) row * (col + 2) else (row+1) * (col+1)
    emitln(s"conf['nPCU']=${pcusS.size}")
    emitln(s"conf['nPMU']=${pmusS.size}")
    emitln(s"conf['nRouter']=${nRouter}")
    emitln(s"conf['nSwitch']=${nSwitch}")
    List("DynHopsVec", "DynHopsScal", "StatHopsVec", "StatHopsScal").foreach { key =>
      emitln(s"conf['$key']=${totalHopCountOf.get(key).getOrElse(0l)}")
    }
    emitln(s"conf['freq']=${compiler.arch.designParam.clockFrequency}")
    psimCycle.foreach { cycle =>
      emitln(s"conf['cycle']=${cycle}")
    }

emitln(s"""
import sys,os
sys.path.insert(0, os.environ['PIR_HOME'] + "bin/")
from plasticine_model import *
model = PlasticineModel(os.environ['PIR_HOME'] + '/spade/' + 'data', tech=28)
area = model.get_area_summary(**conf)
energy = model.get_energy_summary(**conf)
if 'cycle' in conf:
   power = model.get_power_summary(**conf)
else:
   power = {}

def convert_unit(value, unit):
    scale = 1e6 if '^2' in unit else 1e3
    while value >= scale:
        value = value / scale
        if 'f' in unit:
            unit = unit.replace('f', 'p')
        elif 'p' in unit:
            unit = unit.replace('p', 'n')
        elif 'n' in unit:
            unit = unit.replace('n', 'u')
        elif 'u' in unit:
            unit = unit.replace('u', 'm')
        elif 'm' in unit:
            unit = unit.replace('m', '')
    return value, unit

for k in area:
    v,unit = convert_unit(area[k], 'um^2')
    print('{:30s} {:10.2f} ({})'.format(k, v, unit))

for k in energy:
    v,unit = convert_unit(energy[k]*1e15, 'fJ')
    print('{:30s} {:10.2f} ({})'.format(k, v, unit))

for k in power:
    v,unit = convert_unit(power[k]*1e12, 'pW')
    print('{:30s} {:10.2f} ({})'.format(k, v, unit))
""")

  }

}

