package com.company.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.rest.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
	User getUserById(Long id);
	List<User> findByName(String name);
}
