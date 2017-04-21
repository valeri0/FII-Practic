package eu.ubis.fiimdb.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.db.dao.ReviewDao;
import eu.ubis.fiimdb.model.Review;

public class ReviewService {
	private EntityManager entityManager;
	
	ReviewService(){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("fiimdb");
		entityManager = emFactory.createEntityManager();
	}
	
	public ReviewDao mapModelToReviewDao(Review review){
		
		ReviewDao dao = new ReviewDao();
		dao.setCommentary(review.getCommentary());
		dao.setId(review.getId());
		dao.setRating(review.getRating());
		dao.setUsername(review.getUsername());
		
		return dao;
	}
	
	public Review mapReviewDaoToModel(ReviewDao dao){
		Review review = new Review();
		
		review.setId(dao.getId());
		review.setCommentary(dao.getCommentary());
		review.setRating(dao.getRating());
		review.setUsername(dao.getUsername());
		
		return review;
	}
	
	public void addReview(int movieId,Review review){
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		
		entityManager.persist(mapModelToReviewDao(review));
		
		entityManager.find(MovieDao.class, movieId).addReview(mapModelToReviewDao(review));

		
		transaction.commit();
		
	}
	

}
