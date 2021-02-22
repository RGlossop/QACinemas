package dao
import models.{Comment, Comments}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object CommentDAO {
  lazy val db: Database = Database.forConfig("mySqlDB")
  lazy val table: TableQuery[Comments] = TableQuery[Comments]

  def readAll: Future[Seq[Comment]] = {
    db.run(table.result)
  }

  def createComment(comment: Comment): Future[String] = {
    db.run(table += comment).map(res => "Item successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }
}
