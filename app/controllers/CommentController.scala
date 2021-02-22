package controllers

import dao.CommentDAO
import models.{Comment, CommentForm}
import play.api.data.Form
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source
import scala.util.{Failure, Success}

@Singleton
class CommentController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  def create(comment: Comment): Unit = {
    CommentDAO createComment (comment) onComplete {
      case Success(value) =>
        Redirect("/comments")
      case Failure(exception) =>
        exception.printStackTrace()
    }
  }

    def index = Action.async { implicit request =>
      CommentDAO.readAll map {
        commentList =>
          Ok(views.html.comments(commentList, CommentForm.commentForm))
      } recover {
        case error: Exception => InternalServerError("database failed")
      }
   }

  def createComment() = Action { implicit request =>
    CommentForm.commentForm.bindFromRequest.fold({ _ =>
      BadRequest(views.html.index())
    }, {widget =>
      val containsSwear = checkComment(widget.getCommentBody)
      if(containsSwear == true) {
          BadRequest(views.html.index())
      } else {
        create(widget)
        Thread.sleep(2000)
        Redirect("/comments")
      }
    })
  }

  def checkComment(comment: String): Boolean = {
    var isBad = false
    val badWords = Source.fromResource("resources/badwords.txt").getLines().toSet
    val splitWords = comment.split(" ")
    for(word <- badWords) {
      for(splitWord <- splitWords)
      if(splitWord == word) {
        isBad = true
      }
    }
    isBad
  }
}
