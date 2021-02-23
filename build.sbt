name := "QACinemas"
 
version := "1.0"

fork in run := true
      
lazy val `qacinemas` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.4"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

libraryDependencies += "com.github.daddykotex" %% "courier" % "3.0.0-M2a"

libraryDependencies ++= Seq(
	"com.typesafe.slick" %% "slick" % "3.3.3",
	"org.slf4j" % "slf4j-nop" % "1.6.4",
	"com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
	"mysql" % "mysql-connector-java" % "8.0.11"
)

libraryDependencies += "org.bouncycastle" % "bcprov-jdk15on" % "1.68"


libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "3.141.59"
libraryDependencies += "org.scalatestplus" %% "selenium-3-141" % "3.2.2.0" % "test"
libraryDependencies += "com.google.guava" % "guava" % "25.0-jre"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.2" % "test"
libraryDependencies += "org.scalatestplus" %% "mockito-3-4" % "3.2.2.0" % "test"


