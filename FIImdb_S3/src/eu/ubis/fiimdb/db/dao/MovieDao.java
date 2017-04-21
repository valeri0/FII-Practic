package eu.ubis.fiimdb.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="movie",schema="fiimdb")
@NamedQueries({
@NamedQuery(name="getAllMovies",query="SELECT m from MovieDao m join m.directors d join m.genres g order by m.id"),
@NamedQuery(name="getMoviesByName",query="SELECT m from MovieDao m join m.directors d join m.genres g where lower(m.name) like :value order by m.id"),
@NamedQuery(name="getMoviesByGenre",query="SELECT m from MovieDao m join m.directors d join m.genres g where lower(g.name) like :value order by m.id"),
@NamedQuery(name="getMoviesByYear",query="SELECT m from MovieDao m join m.directors d join m.genres g where extract(year from m.releaseDate)=:value order by m.id"),
@NamedQuery(name="getMoviesByDirector",query="SELECT m from MovieDao m join m.directors d join m.genres g where lower(d.firstName) like :value or lower(d.lastName) like :value order by m.id"),
@NamedQuery(name="getMoviesByWriter",query="SELECT m from MovieDao m join m.directors d join m.genres g where lower(m.writer) like :value order by m.id"),
@NamedQuery(name="getMoviesByActor",query="SELECT m from MovieDao m join m.directors d join m.genres g where lower(m.casting) like :value order by m.id "),
@NamedQuery(name="getMoviesByDirectorID", query="SELECT m from MovieDao m join m.directors d where d.id=:value ")
})

public class MovieDao {
	@Id
	@SequenceGenerator(name="movie_seq", schema="fiimdb", sequenceName="movie_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
	private int id;

	@Column( name="release_date" )
	private Date releaseDate;
	private String name;
	private double rating;
	private int length;
	private String casting;
	private String description;
	private String writer;
	private String poster;

	@ManyToMany
	@JoinTable(name="category",
		joinColumns=@JoinColumn(name="MOVIE_ID",referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="GENRE_ID", referencedColumnName="ID"))
	private List<GenreDao> genres;
	
	
	@ManyToMany
	@JoinTable(name="directed",
		joinColumns=@JoinColumn(name="MOVIE_ID",referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="DIRECTOR_ID",referencedColumnName="ID"))
	private List<DirectorDao> directors;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="reviewed",
		joinColumns=@JoinColumn(name="MOVIE_ID", referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="REVIEW_ID", referencedColumnName="ID",unique=true))
	private List<ReviewDao> reviews;
	
	
	
	
	

	public MovieDao() {
		this.genres = new ArrayList<>();

	}
	
	
	
	
		
	public List<ReviewDao> getReviews() {
		return reviews;
	}
	
	public void addReview(ReviewDao review){
		this.reviews.add(review);
	}


	public void setReviews(List<ReviewDao> reviews) {
		this.reviews = reviews;
	}





	public List<DirectorDao> getDirectors() {
		return directors;
	}



	public void setDirectors(List<DirectorDao> directors) {
		this.directors = directors;
	}



	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getCasting() {
		return casting;
	}

	public void setCasting(String casting) {
		this.casting = casting;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public List<GenreDao> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDao> genres) {
		this.genres = genres;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MovieDao)) {
			return false;
		}
		return ((MovieDao) obj).getId() == this.id;
	}

}
