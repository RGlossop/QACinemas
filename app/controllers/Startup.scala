package controllers

import com.google.inject.AbstractModule
import models.{Film, Films}
import slick.jdbc.MySQLProfile.api._

import javax.inject.Singleton
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps



@Singleton
class Startup extends AbstractModule {

  val DB = Database.forConfig("mySqlDB")
  val filmTable = TableQuery[Films]
  val dropSchema = DBIO.seq(filmTable.schema.dropIfExists)
  val initSchema = DBIO.seq(filmTable.schema.createIfNotExists)


  Await.ready(DB.run(dropSchema), 1000 millis)
  Await.ready(DB.run(initSchema), 1000 millis)
  println("startup")

  var allFilms = ArrayBuffer(

    Film(1L,"The Dark Knight","12","After Gordon, Dent and Batman begin an assault on Gotham's organised crime, the mobs hire the Joker, a psychopathic criminal mastermind who offers to kill Batman and bring the city to its knees.","2020-12-12","2021-03-12","images/filmposters/dark_knight.jpg")
    ,Film(2L,"The Secret Life Of Pets","U","Max (Louis C.K.) is a spoiled terrier who enjoys a comfortable life in a New York building until his owner adopts Duke, a giant and unruly canine. During their walk outside, they encounter a group of ferocious alley cats and wind up in a truck that's bound for the pound.","2021-02-01","2021-4-15","images/filmposters/slop.jpeg")
    ,Film(3L,"Wonder Woman 1984","12A","Diana Prince lives quietly among mortals in the vibrant, sleek 1980s -- an era of excess driven by the pursuit of having it all. Though she's come into her full powers, she maintains a low profile by curating ancient artifacts, and only performing heroic acts incognito. But soon, Diana will have to muster all of her strength, wisdom and courage as she finds herself squaring off against Maxwell Lord and the Cheetah, a villainess who possesses superhuman strength and agility.","2021-01-01","2021-3-15","images/filmposters/wonderwoman1984.jpg")
    ,Film(4L,"Jumanji: The Next Level","12A","When Spencer goes missing, the gang returns to Jumanji to travel unexplored territories and help their friend escape the world's most dangerous game.","2021-02-21","2021-3-25","images/filmposters/jumanjifilm.jpg")
  )
  for (film <- allFilms) {
    DB.run(filmTable += film)
  }
}
