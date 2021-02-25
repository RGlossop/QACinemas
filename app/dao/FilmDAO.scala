package dao

import models.{Film, Films}
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class FilmDAO {

  lazy val DB = Database.forConfig("mySqlDB")
  lazy val filmTable = TableQuery[Films]

  def readAll(): Future[Seq[Film]] = {
    DB.run(filmTable.result)
  }

  def readFilm(id: Long): Future[Option[Film]] = {
    DB.run(filmTable.filter(_.film_id === id).result.headOption)
  }
}
