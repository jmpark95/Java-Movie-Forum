package com.fdmgroup.springboot.Model;

import java.util.List;

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
	@JoinTable(
			name="USER_FAVOURITES", 
			joinColumns = @JoinColumn(name = "FK_USER_USERNAME"), 
			inverseJoinColumns = @JoinColumn(name = "FK_MOVIE_NAME")
	)
	private List<Movie> favourites;
	
	@ManyToMany
	@JoinTable(
			name="USER_WATCHLIST", 
			joinColumns = @JoinColumn(name = "FK_USER_USERNAME"), 
			inverseJoinColumns = @JoinColumn(name = "FK_MOVIE_NAME")
	)
	private List<Movie> watchList;
	
	@OneToMany(mappedBy = "username")
	private List<Review> reviews;
	
	
	
	
}
