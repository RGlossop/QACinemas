package controllers

import dao.UserDAO
import models.{DbUser, UserMethods}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents, DiscardingCookie}

import java.util
import javax.inject.{Inject, Singleton}
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

@Singleton
class LoginController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  val userDAO = new UserDAO

  def toLogin = Action { implicit request =>
    Ok(views.html.login()).removingFromSession("clickwithoutlogin")
  }

  def toLoginFromDiscussion = Action { implicit request =>
    Ok(views.html.login()).addingToSession("clickwithoutlogin" -> "discussion")
  }

  def toLoginFromBooking = Action { implicit request =>
    Ok(views.html.login()).addingToSession("clickwithoutlogin" -> "booking")
  }

  // $COVERAGE-OFF$
  def loginSuccess() = Action { implicit request =>

    val u = request.cookies.get("usercheck").mkString.split(",")
    val uu = u(1)
    val p = request.cookies.get("passcheck").mkString.split(",")
    val pp = p(1)
    val usernameCheck = Await.result(userDAO.readUserByUsername(uu), 5000 millis).getOrElse(DbUser(0L, "", "", "", "", "", Array())) // I know this is bad but onComplete means that this fails to return and the method defaults to BadRequest("Failure")

    usernameCheck match {
      case DbUser(usernameCheck.user_id, usernameCheck.first_name, usernameCheck.last_name, usernameCheck.date_of_birth, usernameCheck.username, usernameCheck.email, usernameCheck.password) => if (
        util.Arrays.equals(usernameCheck.password, UserMethods.encryptPass(pp))) {
        request.session.get("clickwithoutlogin").map { log =>

          if (log.contentEquals("discussion")) {
            Redirect(routes.CommentController.index()).addingToSession("userid" -> usernameCheck.user_id.toString).addingToSession("username" -> usernameCheck.username).discardingCookies(DiscardingCookie("usercheck")).discardingCookies(DiscardingCookie("passcheck")).removingFromSession("clickwithoutlogin")
            //Redirect case for when a user has attempted to enter the Discussion page without being logged in

          } else if (log.contentEquals("booking")) {
            Redirect(routes.BookingController.toBookings()).addingToSession("userid" -> usernameCheck.user_id.toString).addingToSession("username" -> usernameCheck.username).discardingCookies(DiscardingCookie("usercheck")).discardingCookies(DiscardingCookie("passcheck")).removingFromSession("clickwithoutlogin")
            //Redirect case for when a user has attempted to book a ticket without logging in

          }
          else {
            BadRequest("Cookie Naming Error").discardingCookies(DiscardingCookie("usercheck")).discardingCookies(DiscardingCookie("passcheck")).removingFromSession("clickwithoutlogin")
            Redirect(routes.LoginController.toLogin()).discardingCookies(DiscardingCookie("usercheck")).discardingCookies(DiscardingCookie("passcheck"))
          }

        }.getOrElse {
          Redirect(routes.HomeController.index()).addingToSession("userid" -> usernameCheck.user_id.toString).addingToSession("username" -> usernameCheck.username).discardingCookies(DiscardingCookie("usercheck")).discardingCookies(DiscardingCookie("passcheck"))
          //Redirect case for when a user directly clicks on the Login button
        }


      } else {

        BadRequest("Invalid Username or Password.").discardingCookies(DiscardingCookie("usercheck")).discardingCookies(DiscardingCookie("passcheck"))
        Redirect(routes.LoginController.toLogin()).discardingCookies(DiscardingCookie("usercheck")).discardingCookies(DiscardingCookie("passcheck"))

      }
      case _ => BadRequest("Case error, should never end up here.").discardingCookies(DiscardingCookie("usercheck")).discardingCookies(DiscardingCookie("passcheck"))
        Redirect(routes.LoginController.toLogin()).discardingCookies(DiscardingCookie("usercheck")).discardingCookies(DiscardingCookie("passcheck"))
    }
  }
  // $COVERAGE-ON$

}
