package com.fdmgroup.springboot.Model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@ManyToMany
	@JoinTable(name = "USER_FAVOURITES", joinColumns = @JoinColumn(name = "FK_USER_USERNAME"), inverseJoinColumns = @JoinColumn(name = "FK_MOVIE_NAME"))
	private List<Movie> favourites;

	@ManyToMany
	@JoinTable(name = "USER_WATCHLIST", joinColumns = @JoinColumn(name = "FK_USER_USERNAME"), inverseJoinColumns = @JoinColumn(name = "FK_MOVIE_NAME"))
	private List<Movie> watchList;

	@OneToMany(mappedBy = "username")
	private List<Review> reviews;

	public User() {
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Movie> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Movie> favourites) {
		this.favourites = favourites;
	}

	public List<Movie> getWatchList() {
		return watchList;
	}

	public void setWatchList(List<Movie> watchList) {
		this.watchList = watchList;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(favourites, password, reviews, username, watchList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(favourites, other.favourites) && Objects.equals(password, other.password)
				&& Objects.equals(reviews, other.reviews) && Objects.equals(username, other.username)
				&& Objects.equals(watchList, other.watchList);
	}
}
