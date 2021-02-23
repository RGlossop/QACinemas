package dao

import models.{Booking, Bookings}
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class BookingDAO {

  lazy val DB = Database.forConfig("mySqlDB")
  lazy val bookingTable = TableQuery[Bookings]

  def addBooking(booking: Booking) = {
    DB.run(bookingTable += booking)
  }

  def readBooking(order_id: String): Future[Option[Booking]] = {
    DB.run(bookingTable.filter(_.order_id === order_id).result.headOption)
  }

  def updateBooking(order_id: String, status: String) = {
    DB.run(bookingTable.filter(_.order_id === order_id).result.headOption).map(res => {
      val booking = res.get
      val newBooking = Booking(booking.booking_id, status, order_id, booking.user_id, booking.screening_id, booking.adult_tickets, booking.children_tickets, booking.concession_tickets, booking.total)
      DB.run(bookingTable.insertOrUpdate(newBooking))
    })
  }
}
