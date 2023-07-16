package com.fdmgroup.springboot.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "REVIEW")
	private String reviewContent;
	
	@Column(name = "RATING")
	private int rating;
	
	@ManyToOne //(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}) Dont need cascading as persisting review doesnt need to persist a movie and user
	//since these entities will already exist. I.e a review can't be created in the first place if there is no user and movie.
	@JoinColumn(name = "FK_TITLE")
	private Movie movie;
	
	@ManyToOne //(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "FK_USERNAME")
	private User user;
	
	
	
	

	public Review() {
		super();
	}

	public Review(String reviewContent, int rating) {
		this.reviewContent = reviewContent;
		this.rating = rating;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewContent=" + reviewContent + ", rating=" + rating + ", movie=" + movie + ", user="
				+ user + "]";
	}
	
	
}
