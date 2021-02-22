package controllers

import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject

class ClassificationController @Inject()(cc: ControllerComponents) extends AbstractController(cc)  {

  def index = Action { implicit request =>
    Ok(views.html.classification())
  }

}
