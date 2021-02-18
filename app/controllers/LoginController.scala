package controllers

import models.LoginForm
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class LoginController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {


  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def toLogin = Action { implicit request =>
    Ok(views.html.login(LoginForm.createLoginForm))
  }

  def loginSuccess = Action { implicit request =>
    val formValidationResult = LoginForm.createLoginForm.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.login(formWithErrors))
    }, { widget =>

      Redirect(routes.HomeController.index()).addingToSession("username" -> widget._1)
    })


  }
}
