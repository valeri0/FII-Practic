package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.DirectorDao;
import eu.ubis.fiimdb.db.dao.GenreDao;
import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.model.Director;
import eu.ubis.fiimdb.model.Movie;

public class MovieService {
	// declaram un entityManager, care va face tranzactiile (insert) catre baza de date
	private EntityManager entityManager;
	private DirectorService directorService;

	
	// in constructor ne instantiem un entityManager
	MovieService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("fiimdb");
		entityManager = emFactory.createEntityManager();
		directorService=ServiceFactory.getDirectorService();
	}

	
	// GETMOVIES folosind JPA
	public List<Movie> getMovies() {
		List<MovieDao> movieEntities = (List<MovieDao>)entityManager.createNamedQuery("getAllMovies").getResultList();
		System.out.println(movieEntities);
		
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
		List<Director> directors = new ArrayList<>();
		for(DirectorDao directorEntity : movie.getDirectors()){
			directors.add(directorService.mapDirectorDaoToModel(directorEntity));
		}
		
		newMovie.setDirectors(directors);
		
		
		newMovie.setGenre(genre);
		
		return newMovie;
		
	}
	
	
	public List<Movie> getMoviesForDirector(int directorId){
		List<Movie> movies = new ArrayList<>();
		
		List<MovieDao> entities = new ArrayList<>();
		entities = (List<MovieDao>)entityManager.createNamedQuery("getMoviesByDirectorID").setParameter("value", directorId).getResultList();
		
		for(MovieDao entity : entities){
			movies.add(mapMovieDaoToMovie(entity));
		}
		
		return movies;
		
	}

	
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
			
			case "director":
				movieEntities = (List<MovieDao>)entityManager.createNamedQuery("getMoviesByDirector").setParameter("value", "%" + value + "%").getResultList();

				break;
			
			case "writer":
				movieEntities = (List<MovieDao>)entityManager.createNamedQuery("getMoviesByWriter").setParameter("value", "%" + value + "%").getResultList();

				break;
			
			case "casting":
				movieEntities = (List<MovieDao>)entityManager.createNamedQuery("getMoviesByActor").setParameter("value", "%" + value+ "%").getResultList();

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

	public Movie getSingleMovie(int id){
		
		Movie movie = new Movie();
		
		movie = mapMovieDaoToMovie(entityManager.find(MovieDao.class, id));
		
		return movie;
	}
	
	public void insertMovie(Movie movie, int[] movieGenreIds,int[] movieDirectorIds) {
		MovieDao movieDao = mapMovieModelToDao(movie);
		
		List<GenreDao> movieGenres = new ArrayList<>();
		for (int movieGenreId: movieGenreIds) {
			GenreDao movieGenre = entityManager.find(GenreDao.class, movieGenreId);
			movieGenres.add(movieGenre);
		}
		
		movieDao.setGenres(movieGenres);
		
		List<DirectorDao> movieDirectors = new ArrayList<>();
		for(int movieDirectorID : movieDirectorIds){
			DirectorDao director = entityManager.find(DirectorDao.class, movieDirectorID);
			movieDirectors.add(director);
		}
		
		movieDao.setDirectors(movieDirectors);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(movieDao);
		transaction.commit();
	}
	
	public void deleteMovie(int id){
		
		MovieDao movieToBeDeleted = entityManager.find(MovieDao.class, id);
		
		entityManager.getTransaction().begin();
		
		entityManager.remove(movieToBeDeleted);
		
		entityManager.getTransaction().commit();
		
	}
	
	public void updateMovie(Movie movie,int[] movieGenreIds,int[] directorIds){
		
		entityManager.getTransaction().begin();
	
		MovieDao movieDao = entityManager.find(MovieDao.class, movie.getId());
		
		movieDao.setCasting(movie.getCasting());
		movieDao.setDescription(movie.getDescription());
		movieDao.setName(movie.getName());
		movieDao.setPoster(movie.getPoster());
		movieDao.setLength(movie.getLength());
		movieDao.setRating(movie.getRating());
		movieDao.setWriter(movie.getWriter());
		//movieDao.setReleaseDate(movie.getReleaseDate());
		
		
		List<GenreDao> movieGenres = new ArrayList<>();
		
		for (int movieGenreId: movieGenreIds) {
			GenreDao movieGenre = entityManager.find(GenreDao.class, movieGenreId);
			movieGenres.add(movieGenre);
		}
		
		movieDao.setGenres(movieGenres);
		
		List<DirectorDao> directors = new ArrayList<>();
		
		for(int directorId : directorIds){
			DirectorDao director = entityManager.find(DirectorDao.class,directorId);
			directors.add(director);
		}
		
		movieDao.setDirectors(directors);
		
		entityManager.getTransaction().commit();
		
		
	}

	public MovieDao mapMovieModelToDao(Movie movie) {
		MovieDao dao = new MovieDao();

		dao.setId(movie.getId());
		dao.setReleaseDate(movie.getReleaseDate());
		dao.setName(movie.getName());
		dao.setRating(movie.getRating());
		dao.setLength(movie.getLength());
		dao.setCasting(movie.getCasting());
		dao.setDescription(movie.getDescription());
		dao.setWriter(movie.getWriter());
		dao.setPoster(movie.getPoster());
		dao.setReleaseDate(movie.getReleaseDate());

		return dao;
	}
}
