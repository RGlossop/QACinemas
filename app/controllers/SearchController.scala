package controllers

import dao.FilmDAO
import models.Film
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.collection.immutable.{Map => iMap}
import scala.collection.mutable.{ArrayBuffer, Map => mMap}
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

@Singleton
class SearchController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  def search(searchTerm: String) = Action { implicit request =>
    val films = getFilms
    val filmsTerms = extractSearchTerms(films)
    val results = sortBySearchTerms(filmsTerms, searchTerm)
    Ok(views.html.searchresults(searchTerm, results))
  }

  // Gets the search term from the GET URI
  def getSearchTermFromURI(getString: String): String = {
    val searchTerm = getString.drop(12).dropRight(14)
    searchTerm
  }

  // Reads all films from database
  def getFilms: Seq[Film] = {
    var futureResult = Seq.empty[Film]
    val films = FilmDAO.readAll()
    val result = Await.result(films, 10 seconds)
    result
  }

  // Takes a sequence of films and returns a map of a film ID mapped to an set of all words
  // that the film's database entry contains, stripping any words of two letters or less
  def extractSearchTerms(films: Seq[Film]): iMap[Long, (String, Array[String])] = {
    val result = mMap.empty[Long, (String, Array[String])]
    for (film <- films) {
      val id = film.film_id
      val title = film.film_name
      var words = splitWords(film.searchIterator.toString)
      words = words.filter(word => word.length > 2)
      result += ((id, (title, words)))
    }
    result.toMap
  }


  // Takes the map of film ids to words, and a search term, and returns an array of film ids and titles
  // ordered by the number of matches
  def sortBySearchTerms(map: iMap[Long, (String, Array[String])], searchTerm: String): Array[(Long, String)] = {
    val searchTerms = splitWords(searchTerm).toSet
    val resultMap = ArrayBuffer.empty[(Long, String, Int, Int)]
    for (film <- map) {
      val termsMatched = checkNumberOfTermsMatching(film, searchTerms)
      val totalMatches = checkTotalNumberOfMatches(film, searchTerms)
      resultMap += ((film._1, film._2._1, termsMatched, totalMatches))
    }
    val result = orderByMatches(resultMap)
    result
  }

  // Takes a string and returns an array of strings, splitting at any non-word character, and transforming to lower case
  def splitWords(string: String): Array[String] = {
    val mixedCaseResult = string.replaceAll("\\W", " ").split(" ").toArray
    val result = mixedCaseResult.map(word => word.toLowerCase)
    result
  }

  // Checks the number of unique search terms matched by a film
  def checkNumberOfTermsMatching(film: (Long, (String, Array[String])), searchTerms: Set[String]): Int = {
    var n = 0
    for (term <- searchTerms) {
      if (film._2._2.toSet.contains(term)) n += 1
    }
    n
  }

  // Checks the total number of matches of all search terms for a film
  def checkTotalNumberOfMatches(film: (Long, (String, Array[String])), searchTerms: Set[String]): Int = {
    var n = 0
    for (term <- searchTerms) {
      for (word <- film._2._2) {
        if (word.equals(term)) n += 1
      }
    }
    n
  }

  // Sorts matches first by number of unique matches, then by total number of matches and returns an
  // array of film ids and titles
  def orderByMatches(resultMap: ArrayBuffer[(Long, String, Int, Int)]): Array[(Long, String)] = {
    val result = ArrayBuffer.empty[(Long, String)]
    val resultMapNoZero = resultMap.filter(entry => entry._3 != 0)
    if (resultMapNoZero.size != 0) {
      var i = resultMapNoZero.head._3
      while (i > 0) {
        val resultForGivenMatches = resultMapNoZero.filter(entry => entry._3 == i).sortBy(entry => entry._4)
        for (entry <- resultForGivenMatches) {
          result += ((entry._1, entry._2))
        }
        i -= 1
      }
    }
    result.toArray.reverse
  }

}
