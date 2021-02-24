package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.ListingsGalleryController
import dao.FilmDAO
import models.Film
import org.mockito.Mockito.when
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ListGalleryController extends FlatSpecBaseClass {

  val filmDAO = mock[FilmDAO]

  "ListGalleryController" should "display the Listing Gallery Page" in {
    val controller = new ListingsGalleryController(Helpers.stubControllerComponents())
    val list = Seq[Film](Film(0L, "Film", "12A", "Desc", "20-20-2020", "20-20-2020", "N/A", "James", "James", 1, "N/A"))
    when(filmDAO.readAll()).thenReturn(Future { list })
    val res = controller.toListings.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "ListGalleryController" should "display the Film Page" in {
    val controller = new ListingsGalleryController(Helpers.stubControllerComponents())
    val film = Film(0L, "Film", "12A", "Desc", "20-20-2020", "20-20-2020", "N/A", "James", "James", 1, "N/A")
    when(filmDAO.readFilm(1)).thenReturn(Future{ Some(film) })
    val res = controller.showFilm(1).apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

  "ListGalleryController" should "display the Login Page" in {
    val controller = new ListingsGalleryController(Helpers.stubControllerComponents())
    val res = controller.bookingsRedirect(1).apply(FakeRequest())
    contentType(res).mustBe(None)
  }

  "ListGalleryController" should "display the Booking Page" in {
    val controller = new ListingsGalleryController(Helpers.stubControllerComponents())
    val res = controller.bookingsRedirect(1).apply(FakeRequest().withSession("userid" -> "1"))
    contentType(res).mustBe(None)
  }

}
