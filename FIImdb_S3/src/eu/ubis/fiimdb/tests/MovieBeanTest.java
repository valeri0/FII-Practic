package eu.ubis.fiimdb.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.model.Director;
import eu.ubis.fiimdb.model.Movie;



public class MovieBeanTest {

	@Test
	public void testSearch() {
		String criteria = "casting";
		String value ="pitt";
		
		MovieBean bean = new MovieBean();
		
		bean.search(criteria, value);
		
		List<Movie> movies = bean.getMovies();
		
		int pittMovies=4;
		
		for(Movie movie : movies){
			if(movie.getCasting().toLowerCase().contains(value)){
				pittMovies--;
			}
		}
	
		assertEquals(0,pittMovies);
		
	}

	@Test
	public void testGetMoviesWithDirectors(){
		MovieBean bean = new MovieBean();
		
		bean.getAllMovies();
		
		List<Movie> movies = bean.getMovies();
		
		for(Movie aMovie : movies){
			System.out.print(aMovie.getName()+": ");
			if(aMovie.getDirectors()==null){
				fail("No directors");
			}
			for(Director aDirector : aMovie.getDirectors()){
				System.out.print(aDirector.getFirstName());
				
			}
		}
		
		
		
		
	}
}
