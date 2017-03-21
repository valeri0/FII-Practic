package eu.ubis.fiimdb.controller;

import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.model.Genre;
import eu.ubis.fiimdb.model.Movie;
import eu.ubis.fiimdb.service.GenreService;
import eu.ubis.fiimdb.service.MovieService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class MovieBean {
	private MovieService movieService = ServiceFactory.getMovieService();
	private GenreService genreService = ServiceFactory.getGenreService();
	private List<Movie> movies = new ArrayList<Movie>();

	public void getAllMovies() {
		movies = movieService.getMovies();		
	}
	
	public void search(String criteria, String value) {
		movies = movieService.search(criteria, value);
	}

	public List<Movie> getMovies() {
		return movies;
	}
	
	public void insertMovie(Movie movie, int[] movieGenreIds) {
		movieService.insertMovie(movie, movieGenreIds);
	}
	/*
	public List<Genre> getGenres() {
		return genreService.getGenres();
	}
	*/
}
