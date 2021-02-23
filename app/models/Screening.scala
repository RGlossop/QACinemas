package models

import slick.jdbc.MySQLProfile.api._

case class Screening(screening_id: Long, film_id: Long, screen: Int, date: String, time: String)

case class Screenings(tag: Tag) extends Table[Screening](tag, "screening") {
  def screening_id = column[Long]("screening_id", O.PrimaryKey, O.AutoInc)

  def film_id = column[Long]("film_id")

  def screen = column[Int]("screen")

  def date = column[String]("date")

  def time = column[String]("time")

  def * = (screening_id, film_id, screen, date, time) <> (Screening.tupled, Screening.unapply)
}

object ScreeningUtil {

  def formatScreening(screening: Screening): String = {
    s"Time: ${screening.time} | Date: ${screening.date}"
  }

}
