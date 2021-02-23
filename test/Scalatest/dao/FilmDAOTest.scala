package Scalatest.dao

import Scalatest.FlatSpecBaseClass
import dao.FilmDAO
import models.{Film, Films}
import org.mockito.Mockito.{doReturn, times, verify}
import slick.jdbc.MySQLProfile.api._

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

class FilmDAOTest extends FlatSpecBaseClass{

  val DB = Database.forConfig("mySqlDB")
  val filmTable = TableQuery[Films]
  val initSchema = DBIO.seq(filmTable.schema.createIfNotExists)
  val dropSchema = DBIO.seq(filmTable.schema.dropIfExists)
  val allFilms = ArrayBuffer(
    Film(0L, "The Test", "18", "A film about performing scala play testing", "2020-02-23", "2020-03-20", "AFilmjpeg", "Lloyd Low, Ryan Glossop, Kieran Hanahoe, Lukasz Dudek", "Piers Barber", 10000, "youtubelink")
    , Film(0L, "The Test: 2", "18", "The saga about play testing continues", "2020-02-23", "2020-03-20", "AFilmjpeg2", "Lloyd Low, Ryan Glossop, Kieran Hanahoe, Lukasz Dudek", "Piers Barber", 100, "youtubelink2")
  ).toSeq
  before{
    Await.result(DB.run(dropSchema), 5000 millis)
    Await.result(DB.run(initSchema), 5000 millis)
    for (film <- allFilms) {
      Await.ready(DB.run(filmTable += film),200 millis)
    }
  }


  "readAll" should "return all films in the database" in{
    Given("A request")



    doReturn(Future(allFilms)).when(FilmDAO).readAll()


    When("the dao tries to read all")
    verify(FilmDAO,times(1)).readAll()
  }


}