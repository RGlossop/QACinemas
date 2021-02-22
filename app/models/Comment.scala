package models

import slick.jdbc.MySQLProfile.api._

case class Comment(comment_id : Long, username: String, comment_body: String) {
  def getUsername() = username
  def getCommentBody() = comment_body
}

case class Comments(tag: Tag) extends Table[Comment](tag, "comments") {

  def comment_id = column[Long]("comment_id", O.PrimaryKey, O.AutoInc)

  def username = column[String]("username")

  def comment_body = column[String]("comment_body")

  def * = (comment_id, username, comment_body) <> (Comment.tupled, Comment.unapply)

}