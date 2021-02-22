package models

import dao.UserDAO
import play.api.data.Form
import play.api.data.Forms._

import java.util
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

case class LoginForm(username: String, password: String)

object LoginForm {


  val createLoginForm = Form(
    tuple(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    ) verifying("Invalid username or password", result => result match {
      case (username,password) => check(LoginForm(username,password))
    }
  )
  )


  def check(log : LoginForm): Boolean = {
    val usernameCheck = UserDAO.readUserByUsername(log.username)
    val dummy = DbUser(0,"f","g","h","j","k",Array(0))
    usernameCheck onComplete {
      case Success(value) => if (util.Arrays.equals(value.getOrElse(dummy).getPassword, UserMethods.encryptPass(log.password))){
        true
      }else{false}

      case Failure(error) => error.printStackTrace
        false
    }
    false

  }



}