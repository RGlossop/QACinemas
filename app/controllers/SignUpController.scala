package controllers

import dao.UserDAO
import models.{SignUpForm, User}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}
import utils.EmailUtils

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

@Singleton
class SignUpController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  def signUpPage() = Action { implicit request =>
    Ok(views.html.signup(SignUpForm.signUpForm))
  }

  def userSignUp() = Action { implicit request =>
    SignUpForm.signUpForm.bindFromRequest().fold({ formWithErrors =>
      BadRequest(views.html.signup(formWithErrors))
    }, { widget =>
      signUp(widget)
      sendConfirmationEmail(widget.getFName, widget.getUsername, widget.getEmail)
      Redirect("/")
    }
    )
  }

  def signUp(user: User): Unit = {
    UserDAO.createUser(user) onComplete {
      case Success(value) =>
        println(value)
      case Failure(exception) =>
        exception.printStackTrace()
    }
  }

  def sendConfirmationEmail(name: String, username: String, email: String): Unit = {
    val subject = "Thanks for signing up with QA Cinemas!"
    val body = s"Hi $name!\n\nThanks for signing up with QA Cinemas. Your username is $username.\n\nRegards\n\nThe QA Cinemas team"
    EmailUtils.sendEmail(subject, body)(address = email)
  }
}
