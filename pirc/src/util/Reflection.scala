package pirc.util

trait Reflection {
  def getDeclaredObjects(a:Any) = {
    a.getClass().getDeclaredFields().map(_.getName).flatMap { name =>
      if (name.endsWith("$module")) {
        val aName = a.getClass.getSimpleName()
        val fieldName = name.replace("$module", "").replace(s"$aName.", "")
        Some(a.getClass().getDeclaredMethod(s"$fieldName").invoke(a))
      } else None
    }.toList
  }
}
