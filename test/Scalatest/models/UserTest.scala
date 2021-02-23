package Scalatest.models

import Scalatest.FlatSpecBaseClass
import models.{DbUser, User, UserMethods}
import org.bouncycastle.jcajce.provider.digest.SHA3

import java.time.LocalDate

class UserTest extends FlatSpecBaseClass {

	val testId = 1L
	val testFName = "Phil"
	val testLName = "Space"
	val testDobDate = LocalDate.of(1990, 1, 1)
	val testDobString = "1990-01-01"
	val testUName = "pspace"
	val testEmail = "test@example.com"
	val testPwd = "password"
	val digestSHA3 = new SHA3.Digest512()
	val testHash = digestSHA3.digest(testPwd.getBytes())

//	"A User object" should "be converted to a DBUser object by fromUser" {
//		val testUser = User(testId, testFName, testLName, testDobDate, testUName, testEmail, testPwd)
//		val testDbUser = DbUser(testId, testFName, testLName, testDobString, testUName, testEmail, testHash)
//		val result = UserMethods.fromUser(testUser)
//		assert(result.equals(testDbUser))
//	}

//	"A DbUser object" should "be converted to a User object by toUser" {
//		val testUser = User(testId, testFName, testLName, testDobDate, testUName, testEmail, testPwd)
//		val testDbUser = DbUser(testId, testFName, testLName, testDobString, testUName, testEmail, testHash)
//		val result = UserMethods.toUser(testDbUser)
//		assert(result.equals(testUser))
//	}
}
