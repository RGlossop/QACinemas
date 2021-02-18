package controllers

import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}


@Singleton
class BookingController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def toBookings = Action {
    Ok(views.html.bookings())
  }


}
