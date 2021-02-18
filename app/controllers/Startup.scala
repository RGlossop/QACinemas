package controllers

import com.google.inject.AbstractModule
import dao.CommentDAO
import dao.CommentDAO.createComment
import models.{Comment, Comments, Films}
import slick.jdbc.MySQLProfile.api._

import scala.language.postfixOps
import javax.inject.Singleton
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt


@Singleton
class Startup extends AbstractModule {

  implicit val DB = Database.forConfig("mySqlDB")
  val filmTable = TableQuery[Films]
  val initSchema = DBIO.seq(filmTable.schema.createIfNotExists)

  val commentTable = TableQuery[Comments]
  val commentsDrop = DBIO.seq(commentTable.schema.dropIfExists)
  val commentsInit = DBIO.seq(commentTable.schema.createIfNotExists)

  Await.ready(DB.run(commentsDrop), 5000 millis)
  Await.ready(DB.run(commentsInit), 5000 millis)

  CommentDAO.createComment(new Comment(0, "Dave", "my Message"))
  CommentDAO.createComment(new Comment(0, "Dave", "my Message"))
  CommentDAO.createComment(new Comment(0, "Dave", "my Message"))

  DB.run(initSchema)
  println("startup")

}
