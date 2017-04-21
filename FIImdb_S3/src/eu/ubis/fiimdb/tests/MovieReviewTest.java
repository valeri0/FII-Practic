package eu.ubis.fiimdb.tests;

import java.util.List;

import org.junit.Test;

import eu.ubis.fiimdb.model.Movie;
import eu.ubis.fiimdb.model.Review;
import eu.ubis.fiimdb.service.ServiceFactory;

public class MovieReviewTest {

	@Test
	public void getMovieReviews() {
		//pentru filmul Godfather, daca lista reviews este nula, testul pica, in caz contrar, testul trece.
		int id=2;
		
		Movie movie = ServiceFactory.getMovieService().getSingleMovie(id);
		
		System.out.println(movie.getName());
		
		List<Review> reviews = movie.getReviews();
		
		System.out.println(reviews.size());
		
	}
	

}
