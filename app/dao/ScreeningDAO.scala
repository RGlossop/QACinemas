package dao

import models.{Screening, Screenings}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.Future

object ScreeningDAO {

  lazy val db = Database.forConfig("mySqlDB")
  lazy val table = TableQuery[Screenings]

  def readAll: Future[Seq[Screening]] = {
    db.run(table.result)
  }

}