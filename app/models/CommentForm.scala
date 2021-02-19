package models

import play.api.data.Form
import play.api.data.Forms._

case class CommentForm(comment_id: Long = 0L, username: String, comment_body: String)

object CommentForm {
  val commentForm = Form(
    mapping(
      "comment_id" -> default(longNumber,0L),
      "username" -> nonEmptyText,
      "comment_body" -> nonEmptyText,
    )(Comment.apply)(Comment.unapply)
  )
}
