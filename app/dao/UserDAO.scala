package dao

import com.sun.tools.javac.jvm.Items
import models.{DbUser, DbUsers, User, UserMethods}

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object UserDAO {

	lazy val db: Database = Database.forConfig("mySqlDB")
	lazy val table: TableQuery[DbUsers] = TableQuery[DbUsers]

	def createUser(user: User): Future[String] = {
		db.run(table += UserMethods.fromUser(user)).map(res => "User successfully created").recover {
			case ex: Exception => ex.getCause.getMessage
		}
	}

	def deleteUser(user_id: Long): Future[Int] = {
		db.run(table.filter(_.user_id === user_id).delete)
	}
}
