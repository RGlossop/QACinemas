package controllers

import dao.{CommentDAO, FilmDAO}
import models.{Comment, CommentForm}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source
import scala.util.{Failure, Success}

@Singleton
class CommentController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  val filmDAO = new FilmDAO
  val commentDAO = new CommentDAO

  def index = Action.async { implicit request =>
    filmDAO.readAll().map { list =>
      Ok(views.html.forum(list))
    }
  }

  def thread(filmid: Int,filmname: String) = Action.async { implicit request =>
    commentDAO.readAll.map { list =>
      Ok(views.html.thread(list.filter(_.film_id == filmid), CommentForm.commentForm, filmid,filmname))
    }
  }

  def createComment(filmid: Int) = Action { implicit request =>
    CommentForm.commentForm.bindFromRequest().fold({ _ =>
      BadRequest(views.html.index())
    }, { widget =>
      val containsSwear = checkComment(widget.comment_body)
      if (containsSwear) {
        BadRequest(views.html.index())
      } else {
        create(Comment(0L, filmid, widget.rating, request.session.get("username").getOrElse("Unknown"), widget.comment_body))
        Redirect("/forum")
      }
    })
  }

  def create(comment: Comment): Unit = {
    commentDAO createComment (comment) onComplete {
      case Success(value) =>
        println("Added Comment")
      case Failure(exception) =>
        exception.printStackTrace()
    }
  }

  def checkComment(comment: String): Boolean = {
    var isBad = false
    val badWords = Source.fromResource("resources/badwords.txt").getLines().toSet
    val splitWords = comment.split(" ")
    for (word <- badWords) {
      for (splitWord <- splitWords)
        if (splitWord == word) {
          isBad = true
        }
    }
    isBad
  }
}
