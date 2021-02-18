package models

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints.pattern

import java.time.ZoneId
import java.util.Date

case class SignUpForm(first_name: String, last_name: String, date_of_birth: Date, username: String, email: String, password: String) {

}

object SignUpForm {
	val signUpForm: Form[User] = Form(
		mapping(
			"first_name" -> nonEmptyText,
			"last_name" -> nonEmptyText,
			"date_of_birth" -> date,
			"username" -> nonEmptyText,
			"email" -> text.verifying(pattern(""".+@.+\..+""".r, error = "Invalid email address")),
			"password" -> nonEmptyText(minLength = 6, maxLength = 64)
		) (formToUser)(userToForm)
	)

	def formToUser(fName: String, lName: String, dateOfBirth: Date, username: String, email: String, password: String): User = {
		val dob = dateOfBirth.toInstant.atZone(ZoneId.systemDefault()).toLocalDate
		val id = 0L
		User(id, fName, lName, dob, username, email, password)
	}

	def userToForm(user: User): Option[(String, String, Date, String, String, String)] = {
		val dob = java.sql.Date.valueOf(user.getDob)
		Some(user.getFName, user.getLName, dob, user.getUsername, user.getEmail, user.getPassword)
	}
}
