package models

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints.pattern

import java.time.ZoneId
import java.util.Date

case class SignUpForm(user_id: Long, first_name: String, last_name: String, date_of_birth: Date, username: String, email: String, password: String) {

}

object SignUpForm {
	val signUpForm = Form(
		mapping(
			"user_id" -> default(longNumber, 0L),
			"first_name" -> nonEmptyText,
			"last_name" -> nonEmptyText,
			"date_of_birth" -> date,
			"username" -> nonEmptyText,
			"email" -> text.verifying(pattern(""".+@.+\..+""".r, error = "Invalid email address")),
			"password" -> nonEmptyText
		) (formToUser)(userToForm)
	)

	def formToUser(id: Long, fName: String, lName: String, dateOfBirth: Date, username: String, email: String, password: String): User = {
		val dob = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
		User(id, fName, lName, dob, username, email, password)
	}

	def userToForm(user: User): Option[(Long, String, String, Date, String, String, String)] = {
		val dob = java.sql.Date.valueOf(user.getDob)
		Some(user.getId, user.getFName, user.getLName, dob, user.getUsername, user.getEmail, user.getPassword)
	}
}
