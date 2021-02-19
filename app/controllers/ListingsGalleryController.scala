package controllers

import dao.FilmDAO
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ListingsGalleryController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def toListings = Action.async { implicit request => {
    FilmDAO.readAll().map { list =>
      Ok(views.html.listingsgallery(list.sortBy(_.film_id)))
    }recover {
      case exception: Exception => exception.printStackTrace(); InternalServerError("Database Failed")
    }
  }
  }



  def showFilm(id: Long) = Action.async { implicit request => {
    FilmDAO.readFilm(id).map { film => Ok(views.html.film(film.get))
    }
  }
  }

  def bookingsRedirect(filmid : Long) = Action { implicit request =>
    Redirect(routes.BookingController.toBookings()).addingToSession("filmid"-> filmid.toString())

  }


}
