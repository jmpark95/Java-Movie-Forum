package com.fdmgroup.springboot.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "REVIEW")
	private String reviewContent;
	
	@Column(name = "RATING")
	private double rating;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "FK_TITLE")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "FK_USERNAME")
	private User user;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	private List<User> likedBy = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	private List<User> dislikedBy = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	private List<User> hahadBy = new ArrayList<>();

 
	

	public Review() {
		super();
	}

	public Review(String reviewContent, double rating) {
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
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

	public List<User> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(List<User> likedBy) {
		this.likedBy = likedBy;
	}

	public List<User> getDislikedBy() {
		return dislikedBy;
	}

	public void setDislikedBy(List<User> dislikedBy) {
		this.dislikedBy = dislikedBy;
	}

	public List<User> getHahadBy() {
		return hahadBy;
	}

	public void setHahadBy(List<User> hahadBy) {
		this.hahadBy = hahadBy;
	}
	
	public List<String> getNamesOfLikedBy(){
		List<String> names = new ArrayList<>();
		
		for (User user : this.getLikedBy()) {
			names.add(user.getUsername());
		}
		
		return names;
	}

	public List<String> getNamesOfDislikedBy(){
		List<String> names = new ArrayList<>();
		
		for (User user : this.getDislikedBy()) {
			names.add(user.getUsername());
		}
		
		return names;
	}
	
	public List<String> getNamesOfHahadBy(){
		List<String> names = new ArrayList<>();
		
		for (User user : this.getHahadBy()) {
			names.add(user.getUsername());
		}
		
		return names;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewContent=" + reviewContent + ", rating=" + rating + ", movie=" + movie
				+ ", user=" + user + ", likedBy=" + likedBy + ", dislikedBy=" + dislikedBy + ", hahadBy=" + hahadBy
				+ "]";
	}
}
