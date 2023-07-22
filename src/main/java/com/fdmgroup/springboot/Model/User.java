package com.fdmgroup.springboot.Model;

import java.util.ArrayList;
import java.util.Date;
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
	
	@Column(name = "CREATED_AT")
	private Date created_at = new Date();
	
	@ManyToMany(mappedBy = "favouritedBy", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	private List<Movie> favourites = new ArrayList<>();
	
	@ManyToMany(mappedBy = "watchlistedBy", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	private List<Movie> watchlist = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany
	private List<User> following = new ArrayList<>();
	
	@ManyToMany(mappedBy = "following")
	private List<User> followers = new ArrayList<>();
	
	
	 
	

	
	

	
	
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
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public void addFollowing(User user) {
		 this.following.add(user);
		 user.getFollowers().add(this);
    }
	
	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	public List<String> getNamesOfFollowing(){
		List<String> names = new ArrayList<>();
		
		for (User user : this.getFollowing()) {
			names.add(user.getUsername());
		}
		
		return names;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(favourites, followers, following, password, reviews, username, watchlist);
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
		return Objects.equals(favourites, other.favourites) && Objects.equals(followers, other.followers)
				&& Objects.equals(following, other.following) && Objects.equals(password, other.password)
				&& Objects.equals(reviews, other.reviews) && Objects.equals(username, other.username)
				&& Objects.equals(watchlist, other.watchlist);
	}
	
	@Override
	public String toString() {
	    return "User [username=" + username + ", password=" + password + ", favourites=" + favourites.size() +
	            ", watchlist=" + watchlist.size() + ", following=" + following.size() + ", followers=" + followers.size() + "]";
	}	
}
