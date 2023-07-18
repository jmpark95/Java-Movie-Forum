package com.fdmgroup.springboot.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;


@Entity
public class User {
	@Id
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@ManyToMany(mappedBy = "favouritedBy", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	private List<Movie> favourites = new ArrayList<>();
	
	@ManyToMany(mappedBy = "watchlistedBy", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	private List<Movie> watchlist = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<Review> reviews = new ArrayList<>();
	
	
	
	
	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, List<Movie> favourites, List<Movie> watchlist) {
		this.username = username;
		this.password = password;
		this.favourites = favourites;
		this.watchlist = watchlist;
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

	public List<Movie> getWatchlist() {
		return watchlist;
	}

	public void setWatchlist(List<Movie> watchlist) {
		this.watchlist = watchlist;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", favourites=" + favourites + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(favourites, password, reviews, username, watchlist);
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
				&& Objects.equals(watchlist, other.watchlist);
	}
	
	

	

}
