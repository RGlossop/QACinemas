package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.ClassificationController
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class ClassificationControllerTest extends FlatSpecBaseClass {

  "ClassificationController" should "display the Classification Page" in {
    val controller = new ClassificationController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.index.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

}
