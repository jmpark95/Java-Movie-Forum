package com.fdmgroup.springboot.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "YEAR OF RELEASE", nullable = false)
	private int releaseYear;
	
	@Column(name = "GENRE", nullable = false)
	private String genre;
	
	@Column(name = "AVERAGE RATING")
	private int rating;
	
	@OneToMany(mappedBy = "movie")
	private List<Review> reviews;
	
	@OneToOne
	@JoinColumn(name = "CREATEDBY_FK_USERNAME")
	private User createdBy;
}
