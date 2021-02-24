package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.NewReleasesController
import dao.FilmDAO
import models.Film
import org.mockito.Mockito.when
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class NewReleasesControllerTest extends FlatSpecBaseClass {

  val filmDAO = mock[FilmDAO]

  "NewReleasesController" should "display the New Releases Page" in {
    val controller = new NewReleasesController(Helpers.stubControllerComponents())
    val list = Seq[Film](Film(0L, "Film", "12A", "Desc", "20-20-2020", "20-20-2020", "N/A", "James", "James", 1, "N/A"))
    when(filmDAO.readAll()).thenReturn(Future {
      list
    })
    val res: Future[Result] = controller.newReleases.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

}
