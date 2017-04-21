package eu.ubis.fiimdb.controller;

import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.model.Genre;
import eu.ubis.fiimdb.model.Movie;
import eu.ubis.fiimdb.model.Review;
import eu.ubis.fiimdb.service.GenreService;
import eu.ubis.fiimdb.service.MovieService;
import eu.ubis.fiimdb.service.ReviewService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class MovieBean {
	
	private MovieService movieService = ServiceFactory.getMovieService();
	private GenreService genreService = ServiceFactory.getGenreService();
	private ReviewService reviewService = ServiceFactory.getReviewService();
	private List<Movie> movies = new ArrayList<Movie>();
	private Movie movieDetail = new Movie();

	public void getAllMovies() {
		movies = movieService.getMovies();		
	}
	
	public void search(String criteria, String value) {
		movies = movieService.search(criteria, value);
	}
	
	public Movie getMovieDetail(){
		return this.movieDetail;
	}

	public List<Movie> getMovies() {
		return movies;
	}
	
	public void insertMovie(Movie movie, int[] movieGenreIds, int[] movieDirectorIds) {
		movieService.insertMovie(movie, movieGenreIds, movieDirectorIds);
	}
	
	public List<Genre> getGenres() {
		return genreService.getGenres();
	}
	
	public void setMovieDetail(int id){
		this.movieDetail=movieService.getSingleMovie(id);
	}
	
	
	
	public void deleteMovie(int id){
		movieService.deleteMovie(id);
	}
	
	public void updateMovie(Movie movie, int[] movieGenreIds,int[] directorIds){
		movieService.updateMovie(movie, movieGenreIds,directorIds);
	}
	
	public void addReview(int movieId, Review review){
		reviewService.addReview(movieId, review);
	}
	
	
	
}
