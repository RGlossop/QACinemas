package controllers

import dao.FilmDAO
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

class NewReleasesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val filmDAO = new FilmDAO

  def newReleases = Action.async { implicit request => {
    filmDAO.readAll().map { list =>
      Ok(views.html.newreleses(list.sortBy(_.opening_date).reverse.dropRight(list.size - 4)))
    } recover {
      case exception: Exception => exception.printStackTrace(); InternalServerError("Database Failed")
    }
  }
  }
}
