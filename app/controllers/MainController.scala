package controllers

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class MainController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{

  def homeRedirect = Action {
    Redirect(routes.HomeController.index)
  }

}
