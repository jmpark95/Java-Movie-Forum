package com.fdmgroup.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.springboot.Model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
