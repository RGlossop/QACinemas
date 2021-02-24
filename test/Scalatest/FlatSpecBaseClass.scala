package Scalatest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.{BeforeAndAfter, BeforeAndAfterEach, GivenWhenThen, Inside, Inspectors, OptionValues}
import org.scalatestplus.mockito.MockitoSugar
import org.specs2.matcher.Matchers


abstract class FlatSpecBaseClass  extends AnyFlatSpec with Matchers
  with OptionValues with Inside with Inspectors with MockitoSugar with BeforeAndAfter with GivenWhenThen with BeforeAndAfterEach

