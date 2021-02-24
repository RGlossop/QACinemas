package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.HomeController
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class HomeControllerTest extends FlatSpecBaseClass {

  "HomeController" should "display the Index Page" in {
    val controller = new HomeController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.index.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "HomeController" should "display the Opening Times Page" in {
    val controller = new HomeController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.openingTimes.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "HomeController" should "display the Getting There Page" in {
    val controller = new HomeController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.gettingThere.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "HomeController" should "display the Places To Go Page" in {
    val controller = new HomeController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.placesToGo.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

}
