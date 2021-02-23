package models

import slick.jdbc.MySQLProfile.api._

case class Comment(comment_id: Long, film_id: Long, rating: Int, username: String, comment_body: String) {
}

case class Comments(tag: Tag) extends Table[Comment](tag, "forum") {

  def comment_id = column[Long]("comment_id", O.PrimaryKey, O.AutoInc)

  def film_id = column[Long]("film_id")

  def rating = column[Int]("rating")

  def username = column[String]("username")

  def comment_body = column[String]("comment_body")

  def * = (comment_id, film_id, rating, username, comment_body) <> (Comment.tupled, Comment.unapply)

}