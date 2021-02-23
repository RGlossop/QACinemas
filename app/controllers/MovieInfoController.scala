package controllers

import models.{Film, Films}
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc.MySQLProfile.api._

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MovieInfoController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  lazy val db = Database.forConfig("mySqlDB")
  lazy val table = TableQuery[Films]

  def movieInfo(id: Long) = Action.async { implicit request => {
    readByID(id).map { film => Ok(views.html.movieinfo(film.get))
    }
  }
  }

  def readByID(id: Long): Future[Option[Film]] = {
    db.run(table.filter(_.film_id === id).result.headOption)
  }

}
