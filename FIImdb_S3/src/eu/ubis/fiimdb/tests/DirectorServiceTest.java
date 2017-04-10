package eu.ubis.fiimdb.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import eu.ubis.fiimdb.model.Director;
import eu.ubis.fiimdb.service.DirectorService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class DirectorServiceTest {

	@Test
	public void testGetDirectorById() {
		

		Director director = new Director();
		
		for(int id=1;id<=10;id++){
		
			director = ServiceFactory.getDirectorService().getDirectorById(id);
		
			if(director.getId()!=id)
				fail("Cannot find director with id = " + id);
		}
		
	}


	@Test
	public void testGetDirectors() {
		
		int totalNumberOfDirectors=11;
		
		List<Director> directors = ServiceFactory.getDirectorService().getDirectors();
		
		assertEquals(totalNumberOfDirectors, directors.size());
		
		
	}
	
	@Test
	public void testDirectorListOfMovies(){
		
		int directorId=7;
		
		int expectedNumber=2;
		
		int directorsMovies;
		
		directorsMovies  = ServiceFactory.getMovieService().getMoviesForDirector(directorId).size();

		
		assertEquals(directorsMovies,expectedNumber);
		
	}
}
