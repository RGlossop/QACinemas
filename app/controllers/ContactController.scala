package controllers

import models.{Email, EmailForm}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}
import utils.EmailUtils

import javax.inject.{Inject, Singleton}

@Singleton
class ContactController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  val testEmail = "example@academytrainee.com"

  def contact = Action { implicit request =>
    Ok(views.html.contact(EmailForm.emailForm))
  }

  // $COVERAGE-OFF$
  def sendEmail = Action { implicit request =>
    EmailForm.emailForm.bindFromRequest().fold({ formWithErrors =>
      BadRequest(views.html.contact(formWithErrors))
    }, { widget =>
      send(widget)
      Redirect("/")
    })
  }

  def send(email: Email) = {
    EmailUtils.sendEmail(email.getSubject(), email.getBody())(replyTo = email.getSender(), address = testEmail)
  }
  // $COVERAGE-ON$
}
