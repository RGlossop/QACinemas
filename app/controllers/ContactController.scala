package controllers

import models.{Email, EmailForm}
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class ContactController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

	val EMAIL_ADDRESS = "kjhanahoe@hotmail.co.uk"

	def contact = Action { implicit request =>
		EmailForm.emailForm.bindFromRequest.fold({ _ =>
			Redirect("/")
		}, {widget =>
			sendEmail(widget)
			Redirect("/")
		})
	}

	def sendEmail(email: Email): Unit = {
	}
}
