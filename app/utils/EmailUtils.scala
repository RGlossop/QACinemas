package utils

import courier.{Envelope, Mailer, Text}

import javax.mail.internet.InternetAddress
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object EmailUtils {

  val sendAddress = "example@googlemail.com"
  val sendPassword = "password"

  // To send email with gmail, if you have two-factor authentication enabled, you need to set up an app password in
  // your google account settings, as courier doesn't support 2fa.
  def sendEmail(subject: String, body: String)(implicit replyTo: String = sendAddress, address: String = sendAddress): Unit = {
    val mailer = Mailer("smtp.gmail.com", 587)
      .auth(true)
      .as(sendAddress, sendPassword)
      .startTls(true)()

    mailer(Envelope
      .from(new InternetAddress(sendAddress))
      .to(new InternetAddress(address))
      .replyTo(new InternetAddress(replyTo))
      .subject(subject)
      .content(Text(body)))
      .onComplete {
        case Success(value) => println("message delivered")
        case Failure(exception) => exception.printStackTrace()
      }
  }

}
