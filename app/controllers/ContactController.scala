package controllers

import models.{Email, EmailForm}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import courier._
import Defaults._

import javax.mail.internet.InternetAddress
import scala.util.{Failure, Success}

@Singleton
class ContactController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{

	val receiveEmail = "kjhanahoe@hotmail.co.uk"

	def contact = Action { implicit request =>
		Ok(views.html.contact(EmailForm.emailForm))
	}

	def sendEmail = Action { implicit request =>
		EmailForm.emailForm.bindFromRequest.fold({ formWithErrors =>
			BadRequest(views.html.contact(formWithErrors))
		}, {widget =>
			send(widget)
			Redirect("/")
		})
	}

	def send(email: Email) = {
		// To send email with gmail, if you have two-factor authentication enabled, you need to set up an app password in
		// your account settings, as courier doesn't support 2fa.
		val mailer = Mailer("smtp.gmail.com", 587)
			.auth(true)
			// removed email
			.as("example@gmail.com", "password")
			.startTls(true)()

		mailer(Envelope
			.from(new InternetAddress(email.getSender))
			.to(new InternetAddress(receiveEmail))
			.replyTo(new InternetAddress(email.getSender))
			.subject(email.getSubject())
			.content(Text(email.getBody())))
			.onComplete {
				case Success(value) => println("message delivered")
				case Failure(exception) => exception.printStackTrace()
			}
	}
}
