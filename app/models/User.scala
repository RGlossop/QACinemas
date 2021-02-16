package models

import slick.jdbc.MySQLProfile.api._

import java.time.LocalDate

import org.bouncycastle.jcajce.provider.digest.SHA3

case class User(user_id: Long = 0, first_name: String, last_name: String, date_of_birth: LocalDate, username: String, email: String, password: String) {
	def getId() = user_id
	def getFName() = first_name
	def getLName() = last_name
	def getDob() = date_of_birth
	def getUsername() = username
	def getEmail() = email
	def getPassword() = password
}

case class DbUser(user_id: Long = 0, first_name: String, last_name: String, date_of_birth: String, username: String, email: String, password: Array[Byte]) {
	def getId(): Long = user_id
	def getFName(): String = first_name
	def getLName() = last_name
	def getDob() = date_of_birth
	def getUsername() = username
	def getEmail() = email
	def getPassword() = password
}

case class Users(tag: Tag) extends Table[DbUser](tag, "users") {
	def user_id = column[Long]("user_id", O.PrimaryKey, O.AutoInc)
	def first_name = column[String]("first_name")
	def last_name = column[String]("last_name")
	def date_of_birth = column[String]("date_of_birth")
	def username = column[String]("username")
	def email = column[String]("email")
	def password = column[Array[Byte]]("password")
	def * = (user_id, first_name, last_name, date_of_birth, username, email, password).<>(userMethods.toDbUser, userMethods.fromUser)
}

object userMethods {

	def toDbUser(data: (Long, String, String, String, String, String, Array[Byte])): DbUser = {
		DbUser(data._1, data._2, data._3, data._4, data._5, data._6, data._7)
	}

	def fromUser(user: User): Option[(Long, String, String, String, String, String, Array[Byte])] = {
		val dob = user.getDob().toString()
		val digestSHA3 = new SHA3.Digest512()
		val pwd = digestSHA3.digest(user.getPassword().getBytes())
		Some(user.user_id, user.first_name, user.last_name, dob, user.username, user.email, pwd)
	}

	def toUser(dbUser: DbUser): User = {
		val dob = stringToDate(dbUser.getDob())
		val pwd = dbUser.getPassword().toString()
		User(dbUser.getId(), dbUser.getFName(), dbUser.getLName(), dob, dbUser.getUsername(), dbUser.getEmail(), pwd)
	}

	private def stringToDate(dateString: String) = {
		val ymd = dateString.split('-')
		LocalDate.of(Integer.parseInt(ymd(0)), Integer.parseInt(ymd(1)), Integer.parseInt(ymd(2)))
	}
}
