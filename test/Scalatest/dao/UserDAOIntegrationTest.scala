package Scalatest.dao

import Scalatest.FlatSpecBaseClass
import dao.UserDAO
import models.{DbUsers, User}
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.specs2.control.Properties.aProperty
import slick.jdbc.MySQLProfile.api._

import java.time.LocalDate
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class UserDAOIntegrationTest extends FlatSpecBaseClass {

  implicit val DB = Database.forConfig("mySqlDB")
  val usersTable = TableQuery[DbUsers]
  val dao = new UserDAO
  before {
    val dropSchema = DBIO.seq(usersTable.schema.dropIfExists)
    val initSchema = DBIO.seq(usersTable.schema.createIfNotExists)
    Await.result(DB.run(dropSchema), Duration.Inf)
    Await.result(DB.run(initSchema), Duration.Inf)
  }
  def addUser(user: User) = { Await.result(dao.createUser(user), Duration.Inf) }
  "A User" should "Be entered into the database on creation" in {
    Given("A User")
    val dob = LocalDate.of(1993, 6,6)
    val user = User(0L,"Test", "Test", dob, "userTest", "testmail@Test.test", "test123")
    When("They are created")
    val result = addUser(user)
    Then("They should be entered into the database")
    result.isDefined shouldBe true
    result shouldBe "User successfully created"

  }

  "UserDAO" should "Read all users when requested" in {
    Given("A User")
    val dob = LocalDate.of(1993, 6,6)
    val user = User(0L,"Test", "Test", dob, "userTest", "testmail@Test.test", "test123")
    addUser(user)

    When("The dao looks for it")
    val result = Await.result(dao.readAllUsers(), Duration.Inf)

    Then("The user will be returned")
    result.size shouldBe 1
    result.head.getId shouldEqual 1L
  }

  "A User" should "be deleted on request" in {
    Given("A User")
    val dob = LocalDate.of(1993, 6,6)
    val user = User(0L,"Test", "Test", dob, "userTest", "testmail@Test.test", "test123")
    addUser(user)

    When("They request to be deleted")
    val result = Await.result(dao.deleteUser(1L), Duration.Inf)

    Then("The a success number will be returned")
    result.isDefined shouldBe true
    result shouldBe 1
  }

  "A user" should "be returned when searched by username" in {
    Given("A User")
    val dob = LocalDate.of(1993, 6,6)
    val user = User(0L,"Test", "Test", dob, "userTest", "testmail@Test.test", "test123")
    addUser(user)

    When("They are searched for")
    val result = Await.result(dao.readUserByUsername("userTest"), Duration.Inf)

    Then("They will be returned")
    result.isDefined shouldBe true
    result.head.getUsername shouldBe "userTest"
  }
  "A user" should "be returned when searched by id" in {
    Given("A User")
    val dob = LocalDate.of(1993, 6,6)
    val user = User(0L,"Test", "Test", dob, "userTest", "testmail@Test.test", "test123")
    addUser(user)

    When("They are searched for")
    val result = Await.result(dao.readUserById(1L), Duration.Inf)

    Then("They will be returned")
    result.isDefined shouldBe true
    result.head.getUsername shouldBe "userTest"
  }
  after {
    val dropSchema = DBIO.seq(usersTable.schema.dropIfExists)
    val initSchema = DBIO.seq(usersTable.schema.createIfNotExists)
    Await.result(DB.run(dropSchema), Duration.Inf)
    Await.result(DB.run(initSchema), Duration.Inf)

    val dateString = "1993-06-06"
    val ymd = dateString.split('-')
    val dob = LocalDate.of(Integer.parseInt(ymd(0)), Integer.parseInt(ymd(1)), Integer.parseInt(ymd(2)))
    val userDAO = new UserDAO
    Await.ready(userDAO.createUser(User(0L, "John", "Smith", dob, "Admin123", "Admin@gmail.com", "Password123")), Duration.Inf)
  }
}
