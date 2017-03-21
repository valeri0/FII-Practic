package eu.ubis.fiimdb.db.entity;

import java.util.Objects;

public class GenreEntity {
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GenreEntity)) {
			return false;
		}
		return ((GenreEntity) obj).getId() == this.id;
	}
	
}
