package Scalatest.controllerstest

import Scalatest.FlatSpecBaseClass
import controllers.{BookingController, PaymentController}
import dao.{BookingDAO, ScreeningDAO}
import models.Screening
import org.mockito.Mockito.when
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.mvc.Result
import play.api.test.Helpers.{contentType, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class BookingControllerTest extends FlatSpecBaseClass {

  val paymentController = mock[PaymentController]
  val screeningDAO = mock[ScreeningDAO]
  val bookingDAO = mock[BookingDAO]

  "BookingController" should "display the booking Page" in {
    val screenings = Future {
      Seq[Screening](Screening(0L, 0L, 1, "22-22-2020", "22:00"))
    }
    when(screeningDAO.readAll).thenReturn(screenings)
    val controller = new BookingController(Helpers.stubControllerComponents(), paymentController)
    val res: Future[Result] = controller.toBookings.apply(FakeRequest().withSession("filmid" -> "0"))
    contentType(res).mustBe(Some("text/html"))
  }

  "BookingController" should "display the Bad Request Page" in {
    val controller = new BookingController(Helpers.stubControllerComponents(), paymentController)
    val res = controller.toPayment.apply(FakeRequest())
    contentType(res).mustBe(Some("text/html"))
  }
}
