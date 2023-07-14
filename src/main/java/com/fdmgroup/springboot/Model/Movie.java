package com.fdmgroup.springboot.Model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Movie {
	@Id
	@Column(name = "TITLE", unique = true, nullable = false)
	private String title;
	
	@Column(name = "YEAR OF RELEASE", nullable = false)
	private int releaseYear;
	
	@Column(name = "GENRE", nullable = false)
	private String genre;
	
	@Column(name = "AVERAGE RATING")
	private int rating;
	
	@OneToMany(mappedBy = "movie")
	private List<Review> reviews;
	
	@ManyToOne
	//@JoinColumn(name = "CREATEDBY_FK_USERNAME", unique = false)
	private User createdBy;

	
	
	
	
	public Movie() {
	}

	public Movie(String title, int releaseYear, String genre, int rating) {
		super();
		this.title = title;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", releaseYear=" + releaseYear + ", genre=" + genre + ", rating=" + rating
				+ "]";
	}

}
