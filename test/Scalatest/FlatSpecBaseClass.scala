package Scalatest

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfter, Inside, Inspectors, OptionValues}

import org.scalatestplus.mockito.MockitoSugar

abstract class FlatSpecBaseClass  extends AnyFlatSpec with Matchers
  with OptionValues with Inside with Inspectors with MockitoSugar with BeforeAndAfter