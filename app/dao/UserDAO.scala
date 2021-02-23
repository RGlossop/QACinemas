package dao

import models.{DbUser, DbUsers, User, UserMethods}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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

	def readAllUsers() : Future[Seq[DbUser]] = {
		db.run(table.result)
	}

	def readUserByUsername(username : String) :Future[Option[DbUser]] ={
		db.run(table.filter(_.username === username ).result.headOption)
	}

	def readUserById(id : Long) :Future[Option[DbUser]] ={
		db.run(table.filter(_.user_id === id ).result.headOption)
	}

}
