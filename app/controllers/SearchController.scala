package controllers

import dao.FilmDAO
import models.Film
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.{Map => iMap}
import scala.collection.mutable.{Map => mMap}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

@Singleton
class SearchController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{

	def search(searchTerm: String) = Action { implicit request =>
		val films = getFilms
		val filmsTerms = extractSearchTerms(films)
		val results = sortBySearchTerms(filmsTerms, searchTerm)

	}

	// Reads all films from database
	def getFilms: Seq[Film] = {
		val films = FilmDAO.readAll()
		var result = Seq.empty[Film]
		films.onComplete{
			case Success(value) =>
				result = value
			case Failure(exception)	=>
				exception.printStackTrace()
		}
		result
	}

	// Takes a sequence of films and returns a map of a film ID mapped to an set of all words
	// that the film's database entry contains, stripping any words of two letters or less
	def extractSearchTerms(films: Seq[Film]): iMap[Long, Set[String]] = {
		val result = iMap.empty[Long, Set[String]]
		for(film <- films){
			val id = film.film_id
			var words = ArrayBuffer.empty[String]
			val commaSplitWords = film.searchIterator.toString().split(", ")
			for(string <- commaSplitWords){
				words += string.split(' ')
			}
			words.filter(word => word.length > 2)
			result += (id, words)
		}
		result
	}

	// Takes the map of film ids to words, and a search term, and returns an array of film ids
	// ordered by the number of times the words in the search term appear in the words array
	def sortBySearchTerms(map: iMap[Long, Set[String]], searchTerm: String): Array[(Long, Int)] = {
		val searchTerms: Set[String] = searchTerm.split(' ').toSet
		val resultMap = mMap.empty[Long, Int]
		for(film <- map){
			var n = 0
			for(term <- searchTerms){
				if(searchTerms.contains(term)) n += 1
			}
			resultMap += (film._1, n)
		}
		val result = resultMap.toSeq.filter(word => word._2 > 0).sortBy(word => word._2).toArray
		result
	}

}
