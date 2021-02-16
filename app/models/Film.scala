package models

import slick.jdbc.MySQLProfile.api._


case class Film(film_id : Long, film_name : String, age_rating : String, description : String, opening_date : Int, closing_date : Int)

case class Films(tag :Tag) extends Table[Film](tag, "films") {

  def film_id = column[Long]("film_id", O.PrimaryKey, O.AutoInc)

  def film_name = column[String]("film_name")

  def age_rating = column[String]("age_rating")

  def description = column[String]("description")

  def opening_date = column[Int]("opening_date")

  def closing_date = column[Int]("closing_date")

  def * = (film_id,film_name,age_rating,description,opening_date,closing_date) <> (Film.tupled,Film.unapply)
}