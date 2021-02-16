package controllers

import models.SignUpForm
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject

class SignUpController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{

	def signUp() = Action { implicit request =>
		Ok(views.html.signup(SignUpForm.signUpForm))
	}
}
