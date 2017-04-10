package eu.ubis.fiimdb.db.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Director",schema="fiimdb")

@NamedQueries({
@NamedQuery(name="getAllDirectors",query="SELECT d from DirectorDao d"),
@NamedQuery(name="getDirectorById", query="SELECT d from DirectorDao d where d.id=:value"),
@NamedQuery(name="getDirectorMovies", query="SELECT m from DirectorDao d join d.movies m where d.id=:value")
})

public class DirectorDao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	@Column(name="description")
	private String bio;
	
	@ManyToMany
	@JoinTable(name="directed",
		joinColumns=@JoinColumn(name="MOVIE_ID",referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="DIRECTOR_ID",referencedColumnName="ID"))
	private List<MovieDao> movies;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public List<MovieDao> getMovies() {
		return movies;
	}
	public void setMovies(List<MovieDao> movies) {
		this.movies = movies;
	}
	
	
	
	
}
