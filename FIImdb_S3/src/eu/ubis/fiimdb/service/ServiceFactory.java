package eu.ubis.fiimdb.service;

public final class ServiceFactory {
	private static MovieService movieService;
	private static GenreService genreService;
	private static DirectorService directorService;
	private static UserService userService;
	private static ReviewService reviewService;

	public static MovieService getMovieService() {
		if (movieService == null) {
			movieService = new MovieService();
		}

		return movieService;
	}
	
	public static ReviewService getReviewService(){
		if(reviewService == null){
			reviewService = new ReviewService();
		}
		return reviewService;
	}
	
	public static DirectorService getDirectorService(){
		if(directorService==null){
			directorService = new DirectorService();
		}
		
		return directorService;
	}

	public static GenreService getGenreService() {
		if (genreService == null) {
			genreService = new GenreService();
		}

		return genreService;
	}
	
	public static UserService getUserService(){
		if(userService == null){
			userService = new UserService();
		}
		
		return userService;
	}
}
