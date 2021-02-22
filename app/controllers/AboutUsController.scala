package controllers

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject

class AboutUsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport  {

  def index = Action {implicit request =>
    Ok(views.html.aboutus())
  }

}
