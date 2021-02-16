package controllers

import com.google.inject.AbstractModule
import models.Films
import slick.jdbc.MySQLProfile.api._

import javax.inject.Singleton


@Singleton
class Startup extends AbstractModule {

  val DB = Database.forConfig("mySqlDB")
  val filmTable = TableQuery[Films]
  val initSchema = DBIO.seq(filmTable.schema.createIfNotExists)



  DB.run(initSchema)
  println("startup")

}
