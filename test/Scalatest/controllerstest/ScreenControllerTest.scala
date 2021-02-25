package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.ScreenController
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class ScreenControllerTest extends FlatSpecBaseClass {

  "ScreenController" should "display the toScreen Page" in {
    val controller = new ScreenController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.toScreens.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "ScreenController" should "display the changeNumber Page" in {
    val controller = new ScreenController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.changeNum.apply(FakeRequest())
    contentType(res).mustBe(None)
  }

  "ScreenController" should "display the toScreen2 Page" in {
    val controller = new ScreenController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.toScreens2.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }
}