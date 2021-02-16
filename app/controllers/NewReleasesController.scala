package controllers

import models.Films
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc.MySQLProfile.api._

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

class NewReleasesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  lazy val db = Database.forConfig("mySqlDB")
  lazy val table = TableQuery[Films]

  def newReleases = Action.async { implicit request => {
    db.run(table.result).map { list =>
      Ok(views.html.newreleses(list.sortBy(_.opening_date).reverse.dropRight(list.size - 4)))
    } recover {
      case exception: Exception => exception.printStackTrace(); InternalServerError("Database Failed")
    }
  }
  }
}
