package controllers

import com.google.inject.AbstractModule
import dao.UserDAO
import models._
import slick.jdbc.MySQLProfile.api._

import java.time.LocalDate
import javax.inject.Singleton
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, DurationInt}
import scala.language.postfixOps


@Singleton
class Startup extends AbstractModule {

  implicit val DB = Database.forConfig("mySqlDB")

  val filmTable = TableQuery[Films]
  val commentTable = TableQuery[Comments]
  val usersTable = TableQuery[DbUsers]
  val bookingTable = TableQuery[Bookings]
  val screeningsTable = TableQuery[Screenings]


  val initSchema = DBIO.seq(filmTable.schema.createIfNotExists, usersTable.schema.createIfNotExists, commentTable.schema.createIfNotExists, bookingTable.schema.createIfNotExists, screeningsTable.schema.createIfNotExists)
  val dropSchema = DBIO.seq(filmTable.schema.dropIfExists, usersTable.schema.dropIfExists, commentTable.schema.dropIfExists, bookingTable.schema.dropIfExists, screeningsTable.schema.dropIfExists)


  Await.ready(DB.run(dropSchema), 5000 millis)
  Await.ready(DB.run(initSchema), 5000 millis)

  val dateString = "1993-06-06"
  val ymd = dateString.split('-')
  val dob = LocalDate.of(Integer.parseInt(ymd(0)), Integer.parseInt(ymd(1)), Integer.parseInt(ymd(2)))
  val userDAO = new UserDAO
  Await.ready(userDAO.createUser(User(0L, "John", "Smith", dob, "Admin123", "Admin@gmail.com", "Password123")), Duration.Inf)

  println("startup")

  val dateString = "1993-06-06"
  val ymd = dateString.split('-')
  val dob = LocalDate.of(Integer.parseInt(ymd(0)), Integer.parseInt(ymd(1)), Integer.parseInt(ymd(2)))
  Await.ready(userDAO.createUser(User(0L, "John", "Smith", dob, "Admin123", "Admin@gmail.com", "Password123")), Duration.Inf)

  var allFilms = Seq(

    Film(1L, "The Dark Knight", "12", "After Gordon, Dent and Batman begin an assault on Gotham's organised crime," +
      " the mobs hire the Joker, a psychopathic criminal mastermind who offers to kill Batman and bring the city to its knees.",
      "2020-12-12", "2021-03-12", "images/filmposters/dark_knight.jpg", "Christian Bale, Michael Caine, Heath Ledger, Gary Oldman, Aaron Eckhart," +
        " Maggie Gyllenhaal, Morgan Freeman", "Christopher Nolan", 152, "https://www.youtube.com/embed/EXeTwQWrcwY")

    , Film(2L, "The Secret Life Of Pets", "U", "Max (Louis C.K.) is a spoiled terrier who enjoys a comfortable life" +
      " in a New York building until his owner adopts Duke, a giant and unruly canine. During their walk outside," +
      " they encounter a group of ferocious alley cats and wind up in a truck that's bound for the pound.", "2021-02-01",
      "2021-4-15", "images/filmposters/slop.jpeg", "Louis C.K., Eric Stonestreet, Kevin Hart, Jenny Slate, Ellie Kemper, Lake Bell," +
        " Dana Carvey, Hannibal Buress, Bobby Moynihan, Steve Coogan, Albert Brooks", "Chris Renaud", 86, "https://www.youtube.com/embed/i-80SGWfEjM")

    , Film(3L, "Wonder Woman 1984", "12A", "Diana Prince lives quietly among mortals in the vibrant, sleek 1980s -- " +
      "an era of excess driven by the pursuit of having it all. Though she's come into her full powers, she maintains a low profile" +
      " by curating ancient artifacts, and only performing heroic acts incognito. But soon, Diana will have to muster all of her strength," +
      " wisdom and courage as she finds herself squaring off against Maxwell Lord and the Cheetah, a villainess who possesses superhuman" +
      " strength and agility.", "2021-01-01", "2021-3-15", "images/filmposters/wonderwoman1984.jpg", "Gal Gadot, Chris Pine, Kristen Wiig, " +
      "Pedro Pascal, Robin Wright, Connie Nielsen", "Patty Jenkins", 151, "https://www.youtube.com/embed/sfM7_JLk-84")

    , Film(4L, "Jumanji: The Next Level", "12A", "When Spencer goes missing, the gang returns to Jumanji to travel unexplored " +
      "territories and help their friend escape the world's most dangerous game.", "2021-02-21", "2021-03-25", "images/filmposters/jumanjifilm.jpg",
      "Dwayne Johnson, Jack Black, Kevin Hart, Karen Gillan, Nick Jonas, Awkwafina, Danny Glover, Danny DeVito", "Jake Kasdan", 123, "https://www.youtube.com/embed/rBxcF-r9Ibs")

    , Film(5L, "Spectre", "12A", "James Bond receives an obscure message from M about a sinister organisation, 'SPECTRE'. " +
      "With the help of Madeleine, he uncovers the conspiracy, only to face an ugly truth.", "2021-12-20", "2021-02-25", "images/filmposters/spectre.jpg",
      "Daniel Craig, Christoph Waltz, Léa Seydoux, Ben Whishaw, Naomie Harris, Dave Bautista, Andrew Scott, Monica Bellucci, Ralph Fiennes",
      "Sam Mendes", 150, "https://www.youtube.com/embed/ujmoYyEyDP8")

    , Film(6L, "Maze Runner: The Scorch Trials", "12A", "Upon escaping the maze, Thomas and his friends are shifted to a facility run " +
      "by Mr Janson. When they realise his intent, they escape, only to face the scorching heat in a lifeless world.", "2021-04-21", "2021-06-05", "images/filmposters/mazerunner2.jpg",
      "Dylan O'Brien, Kaya Scodelario, Thomas Brodie-Sangster, Giancarlo Esposito, Aidan Gillen, Ki Hong Lee, Barry Pepper, Lili Taylor, Patricia Clarkson",
      "Wes Ball", 133, "https://www.youtube.com/embed/SDofO3P2HpE")

    , Film(7L, "The Lion King", "PG", "Simba, a young lion prince, flees his kingdom after the murder of his father, Mufasa. " +
      "Years later, a chance encounter with Nala, a lioness, causes him to return and take back what is rightfully his.", "2021-01-01", "2021-3-03", "images/filmposters/lionking.jpg",
      "Donald Glover, Seth Rogen, Chiwetel Ejiofor, Alfre Woodard, Billy Eichner, John Kani, John Oliver, Beyoncé Knowles-Carter, James Earl Jones",
      "Jon Favreau", 118, "https://www.youtube.com/embed/7TavVZMewpY")

    , Film(8L, "Star Wars: The Rise of Skywalker (Episode IX)", "12A", "The revival of Emperor Palpatine resurrects the battle between " +
      "the Resistance and the First Order while the Jedi's legendary conflict with the Sith Lord comes to a head.", "2021-02-01", "2021-03-30", "images/filmposters/starwars9.jpg",
      "Carrie Fisher, Mark Hamill, Adam Driver, Daisy Ridley, John Boyega, Oscar Isaac, Anthony Daniels, Naomi Ackie, Domhnall Gleeson, Richard E. Grant, " +
        "Lupita Nyong'o, Keri Russell, Joonas Suotamo, Kelly Marie Tran, Ian McDiarmid, Billy Dee Williams", "J.J. Abrams", 142, "https://www.youtube.com/embed/8Qn_spdM5Zg")
  )


  for (film <- allFilms) {
    Await.ready(DB.run(filmTable += film), Duration.Inf)
  }

  var allScreenings = ArrayBuffer(
    Screening(0L, 1L, 1, "2020-04-10", "08:00"), Screening(0L, 1L, 1, "2020-04-11", "12:30"), Screening(0L, 1L, 1, "2020-04-11", "20:00"), Screening(0L, 1L, 1, "2020-04-11", "22:00"),
    Screening(0L, 2L, 1, "2020-04-14", "08:00"), Screening(0L, 2L, 1, "2020-04-14", "12:30"), Screening(0L, 2L, 1, "2020-04-18", "20:00"), Screening(0L, 2L, 1, "2020-04-17", "22:00"),
    Screening(0L, 3L, 1, "2020-04-14", "08:00"), Screening(0L, 3L, 1, "2020-04-14", "12:30"), Screening(0L, 3L, 1, "2020-04-14", "20:00"), Screening(0L, 3L, 1, "2020-04-14", "22:00"),
    Screening(0L, 4L, 1, "2020-04-22", "08:00"), Screening(0L, 4L, 1, "2020-04-22", "12:30"), Screening(0L, 4L, 1, "2020-04-21", "20:00"), Screening(0L, 4L, 1, "2020-04-22", "22:00"),
    Screening(0L, 5L, 1, "2020-04-10", "08:00"), Screening(0L, 5L, 1, "2020-04-21", "12:30"), Screening(0L, 5L, 1, "2020-04-22", "20:00"), Screening(0L, 5L, 1, "2020-04-23", "22:00"),
    Screening(0L, 6L, 1, "2020-04-22", "08:00"), Screening(0L, 6L, 1, "2020-04-29", "12:30"), Screening(0L, 6L, 1, "2020-04-26", "20:00"), Screening(0L, 6L, 1, "2020-04-22", "22:00"),
    Screening(0L, 7L, 1, "2020-04-16", "08:00"), Screening(0L, 7L, 1, "2020-04-03", "12:30"), Screening(0L, 7L, 1, "2020-04-21", "20:00"), Screening(0L, 7L, 1, "2020-04-19", "22:00"),
    Screening(0L, 8L, 1, "2020-04-18", "08:00"), Screening(0L, 8L, 1, "2020-04-04", "12:30"), Screening(0L, 8L, 1, "2020-04-11", "20:00"), Screening(0L, 8L, 1, "2020-04-05", "22:0"),
  )

  for (screen <- allScreenings) {
    Await.ready(DB.run(screeningsTable += screen), Duration.Inf)
  }
}