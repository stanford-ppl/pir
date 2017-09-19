package pirc

trait Design {
  def name = getClass().getSimpleName().replace("$", "")
  override def toString = name

}
