package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;

import eu.ubis.fiimdb.db.dao.GenreDao;
import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.db.entity.GenreEntity;
import eu.ubis.fiimdb.db.entity.MovieEntity;
import eu.ubis.fiimdb.db.repository.MovieRepository;
import eu.ubis.fiimdb.db.repository.RepositoryFactory;
import eu.ubis.fiimdb.model.Movie;

public class MovieService {
	// declaram un entityManager, care va face tranzactiile (insert) catre baza de date
	private EntityManager entityManager;

	
	// in constructor ne instantiem un entityManager
	MovieService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("fiimdb");
		entityManager = emFactory.createEntityManager();
	}

	
	// GETMOVIES folosind JPA
	public List<Movie> getMovies() {
		List<MovieDao> movieEntities = (List<MovieDao>)entityManager.createNamedQuery("getAllMovies").getResultList();
		
		List<Movie> movies = new ArrayList<Movie>();
		
		for(int i=0;i<movieEntities.size();i++){
			if(i==0){
				movies.add(mapMovieDaoToMovie(movieEntities.get(i)));
			}
			else if(i>0){
				if(movieEntities.get(i).equals(movieEntities.get(i-1))){
					continue;
					}
				else{
					movies.add(mapMovieDaoToMovie(movieEntities.get(i)));
				}
			}
			
		}
		
		return movies;
	}
	
	public Movie mapMovieDaoToMovie(MovieDao movie){
		Movie newMovie = new Movie();
		newMovie.setId(movie.getId());
		newMovie.setCasting(movie.getCasting());
		newMovie.setDescription(movie.getDescription());
		newMovie.setDirector(movie.getDirector());
		newMovie.setName(movie.getName());
		newMovie.setPoster(movie.getPoster());
		newMovie.setLength(movie.getLength());
		newMovie.setRating(movie.getRating());
		newMovie.setWriter(movie.getWriter());
		newMovie.setReleaseDate(movie.getReleaseDate());
		
		String genre="";
		for(GenreDao g : movie.getGenres()){
			genre+=g.getName();
			
			if(movie.getGenres().indexOf(g)<movie.getGenres().size()-1){
				genre+=", ";
			}
				
			
		}
		newMovie.setGenre(genre);
		
		return newMovie;
		
	}
	
	// SEARCH folosing JPA

	
	public List<Movie> search(String criteria, String value){
		
		List<MovieDao> movieEntities = null;
		List<Movie> movies = new ArrayList<Movie>();
		
	
		
		switch(criteria){
		
			case "name":
				movieEntities = (List<MovieDao>)entityManager.createNamedQuery("getMoviesByName").setParameter("value", "%" +value +"%").getResultList();
				break;
			
			case "genre":
				movieEntities = (List<MovieDao>)entityManager.createNamedQuery("getMoviesByGenre").setParameter("value", "%" + value + "%").getResultList();

				break;
			
			case "year":
				movieEntities = (List<MovieDao>)entityManager.createNamedQuery("getMoviesByYear").setParameter("value", value).getResultList();
				break;
		
		}
		
		for(int i=0;i<movieEntities.size();i++){
			if(i==0){
				movies.add(mapMovieDaoToMovie(movieEntities.get(i)));
			}
			else if(i>0){
				if(movieEntities.get(i).equals(movieEntities.get(i-1))){
					continue;
					}
				else{
					movies.add(mapMovieDaoToMovie(movieEntities.get(i)));
				}
			}
			
		}
		return movies;
		
	}

	
	// INSERT folosind JPA
	// obiectele Movie si lista de genuri -> informatiile introduse in .jsp 
	// ne cream un obiect de tip MovieDao si ii spunem entityManager-ului sa il introduca in baza de date (entityManager.persist())
	public void insertMovie(Movie movie, int[] movieGenreIds) {
		MovieDao movieDao = mapMovieModelToDao(movie);
		
		List<GenreDao> movieGenres = new ArrayList<>();
		for (int movieGenreId: movieGenreIds) {
			GenreDao movieGenre = new GenreDao();
			movieGenre.setId(movieGenreId);
			movieGenres.add(movieGenre);
		}
		
		movieDao.setGenres(movieGenres);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(movieDao);
		transaction.commit();
	}
	
	

	private MovieDao mapMovieModelToDao(Movie movie) {
		MovieDao dao = new MovieDao();

		dao.setId(movie.getId());
		dao.setReleaseDate(movie.getReleaseDate());
		dao.setName(movie.getName());
		dao.setRating(movie.getRating());
		dao.setLength(movie.getLength());
		dao.setCasting(movie.getCasting());
		dao.setDirector(movie.getDirector());
		dao.setDescription(movie.getDescription());
		dao.setWriter(movie.getWriter());
		dao.setPoster(movie.getPoster());
		dao.setReleaseDate(movie.getReleaseDate());

		return dao;
	}
}
