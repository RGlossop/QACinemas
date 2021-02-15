package models

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.Forms.text.verifying
import play.api.data.validation.Constraints.pattern

case class Email(sender: String, subject: String, body: String) {
	def getSender() = sender
	def getSubject() = subject
	def getBody() = body
}

case class EmailForm(sender: String, subject: String, body: String)
object EmailForm {
	val emailForm = Form(
		mapping(
			"sender" -> nonEmptyText.verifying(pattern(""".+@.+\..+""".r, error = "Invalid email address")),
			"subject" -> nonEmptyText,
			"body" -> nonEmptyText
		) (Email.apply)(Email.unapply)
	)
}
