package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.CommentController
import dao.{CommentDAO, FilmDAO}
import models.{Comment, Film}
import org.mockito.Mockito.when
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CommentControllerTest extends FlatSpecBaseClass {

  val filmDAO = mock[FilmDAO]
  val commentDAO = mock[CommentDAO]

  "CommentController" should "display the Forum Page" in {
    val controller = new CommentController(Helpers.stubControllerComponents())
    val list = Seq[Film](Film(0L, "Film", "12A", "Desc", "20-20-2020", "20-20-2020", "N/A", "James", "James", 1, "N/A"))
    when(filmDAO.readAll()).thenReturn(Future {
      list
    })
    val res: Future[Result] = controller.index.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "CommentController" should "display the Thread Page" in {
    val controller = new CommentController(Helpers.stubControllerComponents())
    val list = Seq[Comment](Comment(0L, 0L, 0, "Jim", "This is a Comment!"))
    when(commentDAO.readAll).thenReturn(Future {
      list
    })
    val res: Future[Result] = controller.thread(1).apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "CommentController" should "check for bad words fail" in {
    val controller = new CommentController(Helpers.stubControllerComponents())
    val comment = Comment(0L, 0L, 0, "Bob", "This film fucking sucks!")
    val res = controller.checkComment(comment.comment_body)
    assert(res)
  }

  "CommentController" should "check for bad words pass" in {
    val controller = new CommentController(Helpers.stubControllerComponents())
    val comment = Comment(0L, 0L, 0, "Bob", "This film is Amazing!")
    val res = controller.checkComment(comment.comment_body)
    assert(!res)
  }

}
