package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import akka.stream.Materializer
import controllers.LoginController
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}
import play.mvc.Http.Cookie

class LoginControllerTest extends FlatSpecBaseClass {

  implicit val mat = mock[Materializer]
  implicit val req = FakeRequest()

  "LoginController" should "display the login Page" in {
    val controller = new LoginController(Helpers.stubControllerComponents())
    val res = controller.toLogin.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "LoginController" should "display the login from discussions Page" in {
    val controller = new LoginController(Helpers.stubControllerComponents())
    val res = controller.toLoginFromDiscussion.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "LoginController" should "display the login from booking Page" in {
    val controller = new LoginController(Helpers.stubControllerComponents())
    val res = controller.toLoginFromBooking.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "LoginController" should "process the login and invalid username/password" in {
    val controller = new LoginController(Helpers.stubControllerComponents())
    val res = controller.loginSuccess().apply(req.withCookies(Cookie.builder("usercheck", "sam").build().asScala(), Cookie.builder("passcheck", "pass").build().asScala()))
    contentType(res).mustBe(None)
  }

//  "LoginController" should "process the login and valid" in {
//    val userDAO = mock[UserDAO]
//    val controller = new LoginController(Helpers.stubControllerComponents())
//    when(util.Arrays.equals(Array[String](_), Array[String](_))).thenReturn(true)
//    when(util.Arrays.equals())
//    val res = controller.loginSuccess().apply(req.withCookies(Cookie.builder("usercheck", "sam").build().asScala(), Cookie.builder("passcheck", "pass").build().asScala()))
//    contentType(res).mustBe(None)
//  }

}
