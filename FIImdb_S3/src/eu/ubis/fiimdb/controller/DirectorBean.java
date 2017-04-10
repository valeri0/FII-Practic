package eu.ubis.fiimdb.controller;

import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.model.Director;
import eu.ubis.fiimdb.model.Movie;
import eu.ubis.fiimdb.service.DirectorService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class DirectorBean {
	private DirectorService directorService = ServiceFactory.getDirectorService();
	private List<Director> directors = new ArrayList<Director>();
	
	private Director directorDetails;

	public List<Director> getDirectors() {
		return directors;
	}
	
	public List<Director> getAllDirectors(){
		return directorService.getDirectors();
	}

	public void setDirectors() {
		this.directors = directorService.getDirectors();
	}

	public Director getDirectorDetails() {
		return directorDetails;
	}

	public void setDirectorDetails(int id) {
		this.directorDetails = directorService.getDirectorById(id);
	}
	
	public List<Movie> getMovies(int id){
		
		return ServiceFactory.getMovieService().getMoviesForDirector(id);
		
	}
}
