package controllers

import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSClient
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.language.postfixOps
import scala.sys.process._

@Singleton
class PaymentController @Inject()(cc: ControllerComponents, ws: WSClient) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def paymentPage = Action { implicit req =>
    val booking = createBooking(100)
    val v2 = booking._2
    Ok(views.html.payment(v2))
  }

  def getAccessToken(): String = {
    val res: String = "curl -v https://api-m.sandbox.paypal.com/v1/oauth2/token -H \"Accept: application/json\" -H \"Accept-Language: en_US\" -u \"#:#\" -d \"grant_type=client_credentials\"" !!
    val json: JsValue = Json.parse(res)
    val access: String = (json \ "access_token").as[String]
    access
  }

  def createBooking(ticketCost: Float): (String, String) = {
    val postData: JsValue = Json.obj(
      "intent" -> "CAPTURE",
      "purchase_units" -> Json.arr(
        Json.obj(
          "reference_id" -> "TICKETS",
          "amount" -> Json.obj(
            "currency_code" -> "GBP",
            "value" -> ticketCost
          )
        )
      ),
      "application_context" -> Json.obj(
        "return_url" -> "http://localhost:9000/paymentcheck",
        "cancel_url" -> "http://localhost:9000/#"
      )
    )
    val token: String = "Bearer " + getAccessToken()
    val jsonResponce = Await.result(ws.url("https://api-m.sandbox.paypal.com/v2/checkout/orders").addHttpHeaders("Content-Type" -> "application/json").addHttpHeaders("Authorization" -> token).post(postData), Duration(5, "seconds")).json
    println(jsonResponce)
    val orderID = (jsonResponce \ "id").as[String]
    val url = (jsonResponce \ "links" \ 1 \\ "href").head.as[String]
    (orderID, url)
  }

  def checkPayment(order: String) = Action { implicit req =>
    val token: String = getAccessToken()
    val res: String = ("curl -v -X POST https://api-m.sandbox.paypal.com/v2/checkout/orders/" + order + "/capture -H \"Content-Type: application/json\" -H \"Authorization: Bearer " + token + "\"" !!)
    if (checkPaymentStatus(res)) {
      Ok("Order has been Successfully Completed!")
    }
    else {
      Ok("Order has not been completed! Oops.")
    }
  }

  def checkPaymentStatus(respo: String): Boolean = {
    try {
      (Json.parse(respo) \ "status").as[String] == "COMPLETED"
    } catch {
      case exception: Exception => false
    }
  }
}
