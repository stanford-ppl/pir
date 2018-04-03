package pir.mapper

trait ResourcePruning { self:PIRPass =>
  val constrains = ListBuffer[Constrain]()
  def prune(pmap:PIRMap):EOption[PIRMap] = {
    flatFold(constrains, pmap) { case (pmap, constrain) => constrain.prune(pmap) }
  }
  
}

