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
    if (printStat) println("")
    if (isStatic(designS) || isDynamic(designS)) genScript
  }

  override def finPass = {
    super.finPass
    if (isStatic(designS) || isDynamic(designS)) {
      psimCycle.foreach { psimCycle =>
        info(Console.GREEN, s"pir", s"simulation_cycle                     ${psimCycle}")
      }
      shellProcess("pir", s"python $outputPath", s"${buildPath(dirName, "area_power.log")}") { line =>
        if (printStat && !verbose) {
          if (line.contains("total_area")) info(Console.GREEN, s"pir", line)
          else if (line.contains("total_energy")) info(Console.GREEN, s"pir", line)
          else if (line.contains("total_power")) info(Console.GREEN, s"pir", line)
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

  def parseCycle = {
    if (psimCycle.isEmpty) {
      val log = s"$dirName/psim.log"
      if (exists(log)) {
        getLines(log).foreach { line =>
          if (line.contains("Simulation complete at cycle:")) {
            psimCycle = Some(line.split("Simulation complete at cycle:")(1).trim.toLong)
          }
        }
      }
    }
  }

  def parseMapping = {
    var active:Map[String, Long] = Map.empty
    def totalActiveOf(cus:List[GlobalContainer]):Long = cus.map { cuP => 
      cuP.collectDown[ContextEnable]().map { ctxEn => countOf(ctxEn).get }.max
    }.sum
    pirMap.right.foreach { pmap =>
      val cumap = pmap.cumap
      if (cumap.usedMap.fmap.map.nonEmpty) {
        val groups:Map[Parameter, List[GlobalContainer]] = cumap.usedMap.bmap.map.groupBy { case (cuS, cuP) => cuS.param }.map { case (param, groups) =>
          param -> groups.map { _._2 }.toList
        }
        def cusOf[P<:Parameter:ClassTag] = {
          val param = assertOneOrLess(groups.keys.filter { case param:P => true; case _ => false }, s"param")
          param.map { param => groups(param) }.getOrElse(Nil)
        }
        def activeOf[P<:Parameter:ClassTag]:Long = totalActiveOf(cusOf[P])
        active += ("pcu" -> activeOf[PCUParam])
        active += ("pmu" -> activeOf[PMUParam])
        active += ("dag" -> activeOf[DramAGParam])
      }
    }
    if (active.isEmpty) {
      val nodeFile = s"$dirName/node.csv"
      if (exists(nodeFile)) {
        val idMapping = getLines(nodeFile).flatMap { line =>
          if (!line.contains("node")) {
            val List(id, tp) = line.split(",").map(_.toInt).toList
            Some((id,tp))
          } else None
        }.toMap
        val cus = compiler.top.collectDown[GlobalContainer]()
        val cuMapping = cus.map { cu =>
          val tp = idMapping(cu.id)
          cu -> (tp match {
            case 0 => "afg"
            case 1 => "mc"
            case 2 => "pmu"
            case 3 => "dag"
            case 4 => "pcu"
          })
        }
        val tpMapping = cuMapping.groupBy { case (cu, tp) => tp }
        active = tpMapping.map { case (tp, group) =>
          val cus = group.map { case (cu, tp) => cu }
          tp -> totalActiveOf(cus)
        }
      }
    }
    active
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
    val dagsS = cellsS.filter { _.param.isInstanceOf[DramAGParam] }
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
    parseCycle
    psimCycle.foreach { cycle =>
      emitln(s"conf['cycle']=${cycle}")
      val active = parseMapping
      if (active.nonEmpty) {
        emitln(s"conf['pcu_total_active'] = ${active.getOrElse("pcu",0l)}")
        emitln(s"conf['pmu_total_active'] = ${active.getOrElse("pmu",0l)}")
        emitln(s"conf['dag_total_active'] = ${active.getOrElse("dag",0l)}")
        if (printStat && verbose) {
          info(s"pcu_total_active = ${active.getOrElse("pcu", 0l)}")
          info(s"pmu_total_active = ${active.getOrElse("pmu", 0l)}")
          info(s"dag_total_active = ${active.getOrElse("dag", 0l)}")
        }
      }
    }
    emitln(s"conf['freq']=${compiler.arch.designParam.clockFrequency}")

emitln(s"""
import sys,os
sys.path.insert(0, os.environ['PIR_HOME'] + "/bin/")
from plasticine_model import *
model = PlasticineModel(os.environ['PIR_HOME'] + '/spade/' + 'data', tech=28)
area = model.get_area_summary(**conf)
if 'cycle' in conf:
   energy = model.get_energy_summary(**conf)
else:
   energy = {}
power = model.get_power_summary(energy, **conf)

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

