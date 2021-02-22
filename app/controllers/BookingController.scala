package controllers

import dao.{BookingDAO, ScreeningDAO}
import models.{Booking, BookingForm}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class BookingController @Inject()(cc: ControllerComponents, pc: PaymentController) extends AbstractController(cc) with I18nSupport {


  val screenDAO = new ScreeningDAO
  val bookingDAO = new BookingDAO


  def toBookings = Action.async { implicit request =>
    screenDAO.readAll.map { list =>
      Ok(views.html.bookings(BookingForm.bookingForm, list.filter(_.film_id == request.session.get("filmid").get.toInt)))
    }
  }

  def toPayment() = Action { implicit request =>
    BookingForm.bookingForm.bindFromRequest.fold({ _ =>
      BadRequest(views.html.index())
    }, widget => {
      val total: Double = (widget.adult_tickets * 12.50) + (widget.children_tickets * 8.50) + (widget.concession_tickets * 5)
      if (total <= 0) {
        Ok(views.html.index())
      }
      else {
        val booking = pc.createBooking(total)
        val v1 = booking._1
        val v2 = booking._2
        bookingDAO.addBooking(Booking(0, "CREATED", v1, 0, widget.screening_id, widget.adult_tickets, widget.children_tickets, widget.concession_tickets, total))
        Ok(views.html.payment(v2, Booking(0, "CREATED", "", 0, widget.screening_id, widget.adult_tickets, widget.children_tickets, widget.concession_tickets, total)))
      }
    })
  }
}
