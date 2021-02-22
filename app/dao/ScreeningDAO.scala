package dao

import models.{Screening, Screenings}
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class ScreeningDAO {

  lazy val db = Database.forConfig("mySqlDB")
  lazy val table = TableQuery[Screenings]

  def readAll: Future[Seq[Screening]] = {
    db.run(table.result)
  }

}