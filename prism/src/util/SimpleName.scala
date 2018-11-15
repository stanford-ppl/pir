package prism
package util

trait SimpleName {
  override def toString = this.getClass.getSimpleName.replace("$","")
}

