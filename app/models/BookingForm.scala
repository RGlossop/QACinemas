package models

import play.api.data.Form
import play.api.data.Forms.{default, longNumber, mapping, number}

case class BookingForm(booking_id: Long = 0L, user_id: Long = 0L, screening_id: Long, adult_tickets: Int = 0, children_tickets: Int = 0, concession_tickets: Int = 0)

object BookingForm {
  val bookingForm = Form(
    mapping(
      "booking_id" -> default(longNumber, 0L),
      "user_id" -> default(longNumber, 0L),
      "screening_id" -> longNumber,
      "adult_tickets" -> default(number, 0),
      "children_tickets" -> default(number, 0),
      "concession_tickets" -> default(number, 0),
    )(BookingForm.apply)(BookingForm.unapply)
  )
}
