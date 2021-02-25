package models

import slick.jdbc.MySQLProfile.api._

case class Film(film_id: Long, film_name: String, age_rating: String, description: String, opening_date: String, closing_date: String, picture: String, cast: String, director: String, runtime: Int, trailer: String) {
  def searchIterator: (String, String, String, String) = {
    (film_name, description, cast, director)
  }
}

case class Films(tag: Tag) extends Table[Film](tag, "films") {
  def film_id = column[Long]("film_id", O.PrimaryKey, O.AutoInc)

  def film_name = column[String]("film_name")

  def age_rating = column[String]("age_rating")

  def description = column[String]("description")

  def opening_date = column[String]("opening_date")

  def closing_date = column[String]("closing_date")

  def picture = column[String]("picture")

  def cast = column[String]("cast")

  def director = column[String]("director")

  def runtime = column[Int]("runtime")

  def trailer = column[String]("trailer")

  def * = (film_id, film_name, age_rating, description, opening_date, closing_date, picture, cast, director, runtime, trailer) <> (Film.tupled, Film.unapply)
}

