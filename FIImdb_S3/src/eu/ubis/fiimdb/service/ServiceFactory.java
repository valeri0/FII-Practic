package eu.ubis.fiimdb.service;

public final class ServiceFactory {
	private static MovieService movieService;
	private static GenreService genreService;
	private static DirectorService directorService;

	public static MovieService getMovieService() {
		if (movieService == null) {
			movieService = new MovieService();
		}

		return movieService;
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
}
