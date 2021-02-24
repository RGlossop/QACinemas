package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.CountController
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}
import services.Counter

import scala.concurrent.Future

class CountControllerTest extends FlatSpecBaseClass {

  "CountController" should "display the next Page" in {
    val controller = new CountController(Helpers.stubControllerComponents(), mock[Counter])
    val res: Future[Result] = controller.count.apply(FakeRequest())
    contentType(res).mustBe(Some("text/plain"))
  }
}
