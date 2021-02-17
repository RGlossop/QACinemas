package controllers

import models.{Email, EmailForm}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import courier._
import Defaults._
import utils.EmailUtils

import javax.mail.internet.InternetAddress
import scala.util.{Failure, Success}

@Singleton
class ContactController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{

	val testEmail = "example@hotmail.co.uk"

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
		EmailUtils.sendEmail(email.getSubject(), email.getBody())(replyTo = email.getSender(), address = testEmail)
	}
}
