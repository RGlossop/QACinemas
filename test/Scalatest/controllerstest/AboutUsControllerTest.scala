package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.AboutUsController
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.test.Helpers._
import play.api.mvc._
import play.api.test._

import scala.concurrent.Future

class AboutUsControllerTest extends FlatSpecBaseClass{

  "AboutUsController" should "display the About Us Page" in {
    val controller = new AboutUsController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.index.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

}
