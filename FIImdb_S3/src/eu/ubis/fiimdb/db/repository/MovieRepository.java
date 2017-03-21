package eu.ubis.fiimdb.db.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.db.entity.GenreEntity;
import eu.ubis.fiimdb.db.entity.MovieEntity;

public class MovieRepository {
	private static final String GET_ALL_MOVIES_SQL = "SELECT "
			+ "movie.ID ID, "
			+ "movie.RELEASE_DATE RELEASE_DATE, "
			+ "movie.NAME NAME,movie.RATING RATING,"
			+ "movie.LENGTH LENGTH,"
			+ "movie.CASTING CASTING,"
			+ "movie.DIRECTOR DIRECTOR,"
			+ "movie.DESCRIPTION DESCRIPTION,"
			+ "movie.WRITER WRITER,"
			+ "movie.POSTER POSTER,"
			+ "genre.TYPE TYPE,"
			+ "genre.ID GENRE_ID "
			+ "FROM movie "
			+ "left outer join category on movie.id=category.movie_id "
			+ "left outer join genre on category.genre_id=genre.id";


	public List<MovieEntity> getAllMovies() {
		Connection con = ConnectionHelper.getConnection();
		List<MovieEntity> movies = new ArrayList<MovieEntity>();

		try {
			ResultSet resultSet = con.createStatement().executeQuery(GET_ALL_MOVIES_SQL);

			while (resultSet.next()) {

				MovieEntity movie = new MovieEntity();
				
				movie.setId(resultSet.getInt("id"));
				movie.setReleaseDate(resultSet.getDate("release_date"));
				movie.setName(resultSet.getString("name"));
				movie.setRating(resultSet.getDouble("rating"));
				movie.setLength(resultSet.getInt("length"));
				movie.setCasting(resultSet.getString("casting"));
				movie.setDirector(resultSet.getString("director"));
				movie.setDescription(resultSet.getString("description"));
				movie.setWriter(resultSet.getString("writer"));
				
				GenreEntity aGenre = new GenreEntity();
				aGenre.setName(resultSet.getString("type"));
				aGenre.setId(resultSet.getInt("genre_id"));
				
				movie.setPoster(resultSet.getString("poster"));
				
				//verificam daca avem deja un movieEntity, pentru a-i adauga un nou gen
				
				if(movies.contains(movie)){
					movies.get(movies.indexOf(movie)).getGenres().add(aGenre);
					continue;
				}
				else
				{
					movie.getGenres().add(aGenre);
					movies.add(movie);
				}
				
			

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}
	public List<MovieEntity> search(String criteria, String value){
		Connection con = ConnectionHelper.getConnection();
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		
		
		String query = GET_ALL_MOVIES_SQL + " WHERE ";
		
		switch (criteria.toLowerCase()){
		
		case "genre":
			query+=" lower(type)=\'"+value+"\'";
			break;
		
		case "name":
			query+=" lower(name) like \'%"+value+"%\'";
			break;
			
		case "year":	
			query+=" extract(year from release_date) = " + Integer.parseInt(value);
			break;
		
		default :
			query=GET_ALL_MOVIES_SQL;
			break;
		
		}
		
		try {
			ResultSet resultSet = con.createStatement().executeQuery(query);

			while (resultSet.next()) {
				MovieEntity movie = new MovieEntity();
				
				movie.setId(resultSet.getInt("id"));
				movie.setReleaseDate(resultSet.getDate("release_date"));
				movie.setName(resultSet.getString("name"));
				movie.setRating(resultSet.getDouble("rating"));
				movie.setLength(resultSet.getInt("length"));
				movie.setCasting(resultSet.getString("casting"));
				movie.setDirector(resultSet.getString("director"));
				movie.setDescription(resultSet.getString("description"));
				movie.setWriter(resultSet.getString("writer"));
				
				GenreEntity aGenre = new GenreEntity();
				aGenre.setName(resultSet.getString("type"));
				aGenre.setId(resultSet.getInt("genre_id"));
				
				movie.setPoster(resultSet.getString("poster"));
				
				//verificam daca avem acelasi MovieEntity, pentru a-i adauga un nou GenreEntity
				
				if(movies.contains(movie)){
					movies.get(movies.indexOf(movie)).getGenres().add(aGenre);
					continue;
				}
				else
				{
					movie.getGenres().add(aGenre);
					movies.add(movie);
				}
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return movies;
	}
}
