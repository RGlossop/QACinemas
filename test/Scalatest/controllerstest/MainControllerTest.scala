package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.MainController
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class MainControllerTest extends FlatSpecBaseClass {

  "MainController" should "display the Home Page" in {
    val controller = new MainController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.homeRedirect.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "MainController" should "display the Home Page" in {
    val controller = new MainController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.logout.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }
}
