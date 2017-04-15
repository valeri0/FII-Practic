package eu.ubis.fiimdb.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.UserDao;
import eu.ubis.fiimdb.model.User;

public class UserService {
	
	private EntityManager entityManager;
	
	UserService(){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("fiimdb");
		entityManager = emFactory.createEntityManager();
	}
	
	public UserDao mapModelToUserDao(User user){
		
		UserDao dao = new UserDao();
		
		dao.setId(user.getId());
		dao.setUsername(user.getUsername());
		dao.setPassword(user.getPassword());
		dao.setFirstName(user.getFirstName());
		dao.setLastName(user.getLastName());
		dao.setEmail(user.getEmail());
		
		
		return dao;
	}
	
	public User mapUserDaoToModel(UserDao dao){
		User user = new User();
		
		user.setId(dao.getId());
		user.setPassword(dao.getPassword());
		user.setUsername(dao.getUsername());
		user.setFirstName(dao.getFirstName());
		user.setLastName(dao.getLastName());
		user.setEmail(dao.getEmail());
		
		return user;
	}
	
	public void registerNewUser(User user){
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		entityManager.persist(mapModelToUserDao(user));
		
		transaction.commit();
		
	}

	public User getUserByUsername(String username){
		User user = new User();
		
		user = mapUserDaoToModel(entityManager.find(UserDao.class, username));
		
		return user;
	}
	
	public void updateUser(String username, String firstName, String lastName, String email){
		
		entityManager.getTransaction().begin();
		
		UserDao userDao = entityManager.find(UserDao.class, username);
		
		userDao.setFirstName(firstName);
		userDao.setLastName(lastName);
		userDao.setEmail(email);
		
		entityManager.getTransaction().commit();
		
	}
}
