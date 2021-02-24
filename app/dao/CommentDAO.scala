package dao

import models.{Comment, Comments}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.Future

class CommentDAO {

  lazy val db: Database = Database.forConfig("mySqlDB")
  lazy val table: TableQuery[Comments] = TableQuery[Comments]

  def readAll: Future[Seq[Comment]] = {
    db.run(table.result)
  }

  def createComment(comment: Comment) = {
    db.run(table += comment)
  }

}
