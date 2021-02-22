package models

import slick.jdbc.MySQLProfile.api._

case class Booking(booking_id: Long, status: String, order_id: String, user_id: Long, screening_id: Long, adult_tickets: Int, children_tickets: Int, concession_tickets: Int, total: Double)

case class Bookings(tag: Tag) extends Table[Booking](tag, "bookings") {

  def booking_id = column[Long]("booking_id", O.PrimaryKey, O.AutoInc)

  def status = column[String]("status")

  def order_id = column[String]("order_id")

  def user_id = column[Long]("user_id")

  def screening_id = column[Long]("screening_id")

  def adult_tickets = column[Int]("adult_tickets")

  def children_tickets = column[Int]("children_tickets")

  def concession_tickets = column[Int]("concession_tickets")

  def total = column[Double]("total")

  def * = (booking_id, status, order_id, user_id, screening_id, adult_tickets, children_tickets, concession_tickets, total) <> (Booking.tupled, Booking.unapply)
}