package pirc.test

import org.scalatest._

abstract class UnitTest extends FlatSpec with Matchers with
  OptionValues with Inside with Inspectors

object WIP extends Tag("WIP")
object ARCH extends Tag("ARCH")

