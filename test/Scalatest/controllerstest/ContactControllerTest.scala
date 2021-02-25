package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.ContactController
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class ContactControllerTest extends FlatSpecBaseClass {

  "ContactController" should "display the Contact Page" in {
    val controller = new ContactController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.contact.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }
}
