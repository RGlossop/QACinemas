package Scalatest.dao

import Scalatest.FlatSpecBaseClass
import dao.BookingDAO
import models.{Booking, Bookings}
import org.scalatest.BeforeAndAfter
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}

class BookingDAOTest extends FlatSpecBaseClass with BeforeAndAfter {

  val DB = Database.forConfig("mySqlDBTest")
  val bookingTable = TableQuery[Bookings]

  before {
    Await.ready(DB.run(bookingTable.schema.dropIfExists), 5 seconds)
    Await.ready(DB.run(bookingTable.schema.createIfNotExists), 5 seconds)
  }

  "BookingDAO" should "Add a booking" in {
    val dao = new BookingDAO("mySqlDBTest")
    val booking = Booking(0L, "CREATED", "", 0L, 0L, 1, 1, 1, 1)
    val result = Await.result(dao.addBooking(booking), 5 seconds)
    assert(result == 1)
  }

  "BookingDAO" should "read booking with given order_id" in {
    val dao = new BookingDAO("mySqlDBTest")
    val booking = Booking(0L, "CREATED", "XXFDDX", 0L, 0L, 1, 1, 1, 1)
    Await.ready(dao.addBooking(booking), 5 seconds)
    val res = Await.result(dao.readBooking("XXFDDX"), 5 seconds)
    assert(booking.order_id.equalsIgnoreCase(res.get.order_id))
  }

  "BookingDAO" should "update booking order using order_id" in {
    val dao = new BookingDAO("mySqlDBTest")
    val booking = Booking(0L, "CREATED", "XXFDDX", 0L, 0L, 1, 1, 1, 1)
    Await.ready(dao.addBooking(booking), 5 seconds)
    val res = Await.result(dao.updateBooking("XXFDDX", "DONE"), 5 seconds)
    assert(res.toString == Future(1).toString)
  }

}
