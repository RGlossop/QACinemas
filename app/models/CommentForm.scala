package models

import play.api.data.Form
import play.api.data.Forms._

case class CommentForm(comment_id: Long = 0L, film_id: Long = 0L, rating: Int, username: String = "Unknown", comment_body: String)

object CommentForm {
  val commentForm = Form(
    mapping(
      "comment_id" -> default(longNumber, 0L),
      "film_id" -> default(longNumber, 0L),
      "rating" -> default(number(min = 1, max = 5), 1),
      "username" -> default(nonEmptyText, "Unknown"),
      "comment_body" -> default(nonEmptyText, "10/10"),
    )(Comment.apply)(Comment.unapply)
  )
}
