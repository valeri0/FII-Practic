package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.DirectorDao;
import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.model.Director;
import eu.ubis.fiimdb.model.Movie;

public class DirectorService {
	private EntityManager entityManager;
	DirectorService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("fiimdb");
		entityManager = emFactory.createEntityManager();
	}
	
	public Director mapDirectorDaoToModel(DirectorDao dao){
		Director director = new Director();
		
		director.setId(dao.getId());
		director.setFirstName(dao.getFirstName());
		director.setLastName(dao.getLastName());
		director.setDateOfBirth(dao.getDateOfBirth());
		director.setBio(dao.getBio());
		
		return director;
	}
	
	
	public Director getDirectorById(int id){
		
		
		
		Director director = new Director();
		
		director = mapDirectorDaoToModel((DirectorDao)entityManager.createNamedQuery("getDirectorById").setParameter("value",id).getSingleResult());

		return director;
	}
	
	public List<Director> getDirectors(){
		List<DirectorDao> entities = (List<DirectorDao>)entityManager.createNamedQuery("getAllDirectors").getResultList();
		
		List<Director> directors = new ArrayList<>();
		
		for(DirectorDao entity : entities){
			directors.add(mapDirectorDaoToModel(entity));
		}
		
		return directors;
	}
	
}
