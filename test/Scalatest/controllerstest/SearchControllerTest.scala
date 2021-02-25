package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.SearchController
import dao.FilmDAO
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class SearchControllerTest extends FlatSpecBaseClass {

  val filmDAO = mock[FilmDAO]

  "SearchController" should "display the Search Results Page" in {
    val controller = new SearchController(Helpers.stubControllerComponents())
    val res: Future[Result] = controller.search("Lion King").apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }

}
