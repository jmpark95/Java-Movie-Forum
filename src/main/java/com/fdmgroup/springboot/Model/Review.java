package com.fdmgroup.springboot.Model;

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
	private String review;
	
	@Column(name = "RATING")
	private int rating;
	
	@ManyToOne
	@JoinColumn(name = "FK_MOVIE_TITLE")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "FK_USERNAME")
	private User username;
}
