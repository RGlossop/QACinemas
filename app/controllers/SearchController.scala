package controllers

import dao.FilmDAO
import models.Film
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.{Map => iMap}
import scala.collection.mutable.{Map => mMap}
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}
import scala.language.postfixOps

@Singleton
class SearchController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{

	def search(searchTerm: String) = Action { implicit request =>
		val films = getFilms
		val filmsTerms = extractSearchTerms(films)
		val results = sortBySearchTerms(filmsTerms, searchTerm)
		Ok(views.html.searchresults(searchTerm, results))
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
	def extractSearchTerms(films: Seq[Film]): iMap[Long, (String, Set[String])] = {
		val result = mMap.empty[Long, (String, Set[String])]
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
	// ordered by the number of times the words in the search term appear in the words array
	def sortBySearchTerms(map: iMap[Long, (String, Set[String])], searchTerm: String): Array[(Long, String)] = {
		val searchTerms = splitWords(searchTerm)
		val resultMap = ArrayBuffer.empty[(Long, String, Int)]
		for(film <- map){
			var n = 0
			for(term <- searchTerms){
				if(film._2._2.contains(term)) {
					n += 1
				}
			}
			resultMap += ((film._1, film._2._1, n))
		}
		var result = ArrayBuffer.empty[(Long, String)]
		for(entry <- resultMap.sortBy(entry => entry._3)){
			if(entry._3 != 0) {
				result += ((entry._1, entry._2))
			}
		}
	result.toArray
	}

	// Takes a string and returns an array of strings, splitting at any non-word character, and transforming to lower case
	def splitWords(string: String): Set[String] = {
		val mixedCaseResult = string.replaceAll("\\W", " ").split(" ").toSet
		val result = mixedCaseResult.map(word => word.toLowerCase)
		result
	}

}
