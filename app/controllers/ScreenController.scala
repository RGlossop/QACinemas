package controllers

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class ScreenController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  var num = 0


  def toScreens = Action { implicit request =>
    num = 1
    Ok(views.html.screens(num))
  }

  def changeNum = Action { implicit request =>
    num += 1
    Redirect(routes.ScreenController.toScreens2())

  }

  def toScreens2 = Action { implicit request =>
    Ok(views.html.screens(num))
  }


}
