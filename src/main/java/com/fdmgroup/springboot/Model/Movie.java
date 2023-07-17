package com.fdmgroup.springboot.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@OneToMany(mappedBy = "movie") //, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
	private List<Review> reviews = new ArrayList<>();
	
	@Column(name = "AVERAGE RATING")
	private double rating;
	
	@Column(name = "RATING_HISTORY")
	@ElementCollection
	private List<Double> ratingHistory = new ArrayList<>();



	


	
	public Movie() {
	}
	
	public Movie(String title, int releaseYear, String genre, double rating) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.rating = rating;
	}

	public Movie(String title, int releaseYear, String genre, double rating, List<User> favouritedBy, List<User> watchlistedBy) {
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		//this.rating = rating;
	    this.ratingHistory.add(rating);
	    calculateAverageRating();
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
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	

	public List<Double> getRatingHistory() {
		return ratingHistory;
	}

	public void setRatingHistory(List<Double> ratingHistory) {
		this.ratingHistory = ratingHistory;
	}
	
//	public void setRatingHistory(Double rating) {
//		this.ratingHistory.add(rating);
//	}



	private void calculateAverageRating() {
	    double sum = 0;
	    for (Double rating : ratingHistory) {
	        sum += rating;
	    }
	    if (!ratingHistory.isEmpty()) {
	        this.rating = roundToOneDecimal(sum / ratingHistory.size());
	    } else {
	        this.rating = 0;
	    }
	}
	
	private double roundToOneDecimal(Double value) {
		DecimalFormat numberFormat = new DecimalFormat("#.0");
		
		return Double.parseDouble(numberFormat.format(value));

	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", releaseYear=" + releaseYear + ", genre=" + genre + ", rating=" + rating
				+ "]";
	}

	
	
	

}
