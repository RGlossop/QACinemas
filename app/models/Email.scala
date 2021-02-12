package models

import play.api.data.Form
import play.api.data.Forms._

case class Email(sender: String, subject: String, body: String) {
	def getSender() = sender
	def getSubject() = subject
	def getBody() = body
}

case class EmailForm(sender: String, subject: String, body: String)
object EmailForm {
	val emailForm = Form(
		mapping(
			"sender" -> nonEmptyText,
			"subject" -> text,
			"body" -> nonEmptyText
		) (Email.apply)(Email.unapply)
	)
}
