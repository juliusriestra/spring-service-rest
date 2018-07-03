package com.company.rest.service;

import java.util.List;

import com.company.rest.entity.User;

public interface UserService {
	 public User saveUser(User user);
	 public List<User> getAllUsers();
	 public User getUserById(Long id);
	 public List<User> findByUserName(String userName);
	 public User updateUser(User inUser);
	 public int deleteUser(Long id);
	 public User updatePartial(User user, long id);
}
