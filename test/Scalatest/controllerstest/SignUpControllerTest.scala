package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.SignUpController
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class SignUpControllerTest extends FlatSpecBaseClass {

  "SignUpController" should "display the Signup Page" in {
    val controller = new SignUpController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.signUpPage().apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }
}
