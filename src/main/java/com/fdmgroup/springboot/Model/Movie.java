package com.fdmgroup.springboot.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

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
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(
			name = "USER_FAVOURITES", 
			joinColumns = @JoinColumn(name = "FK_TITLE"),
			inverseJoinColumns = @JoinColumn(name = "FK_USERNAME"))
	private List<User> favouritedBy = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(
			name = "USER_WATCHLIST", 
			joinColumns = @JoinColumn(name = "FK_TITLE_WATCHLIST"), 
			inverseJoinColumns = @JoinColumn(name = "FK_USERNAME_WATCHLIST"))
	private List<User> watchlistedBy = new ArrayList<>();


	


	
	public Movie() {
	}
	
	public Movie(String title, int releaseYear, String genre, int rating) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.rating = rating;
	}

	public Movie(String title, int releaseYear, String genre, int rating, List<User> favouritedBy, List<User> watchlistedBy) {
		super();
		this.title = title;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.rating = rating;
		this.favouritedBy = favouritedBy;
		this.watchlistedBy = watchlistedBy;
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

	public List<User> getFavouritedBy() {
		return favouritedBy;
	}

	public void setFavouritedBy(List<User> favouritedBy) {
		this.favouritedBy = favouritedBy;
	}

	public List<User> getWatchlistedBy() {
		return watchlistedBy;
	}

	public void setWatchlistedBy(List<User> watchlistedBy) {
		this.watchlistedBy = watchlistedBy;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", releaseYear=" + releaseYear + ", genre=" + genre + ", rating=" + rating
				+ ", favouritedBy=" + favouritedBy + ", watchlistedBy=" + watchlistedBy + "]";
	}
	
	

}
