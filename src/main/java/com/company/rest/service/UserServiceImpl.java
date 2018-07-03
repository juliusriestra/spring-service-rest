package com.company.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.rest.entity.User;
import com.company.rest.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User getUserById(Long id) {
		User inUser = userRepository.getUserById(id);
		User outUser = null;
		if (inUser != null) {
			outUser = new User();
			BeanUtils.copyProperties(inUser, outUser);
		}
		return outUser;
	}

	@Override
	public List<User> findByUserName(String name) {
		List<User> allusers = userRepository.findByName(name);
		List<User> outusers = new ArrayList<User>();
		for (User outUser : allusers) {
			User inUser = new User();
			BeanUtils.copyProperties(outUser, inUser);
			outusers.add(inUser);
		}
		return outusers;
	}

	@Override
	public User saveUser(User inUser) {
		User outUser = new User();
		BeanUtils.copyProperties(inUser, outUser);
		outUser = userRepository.save(outUser);
		BeanUtils.copyProperties(outUser, inUser);
		return inUser;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allusers = userRepository.findAll();
		List<User> outusers = new ArrayList<User>();
		for (User outUser : allusers) {
			User inUser = new User();
			BeanUtils.copyProperties(outUser, inUser);
			outusers.add(inUser);
		}
		return outusers;
	}

	@Override
	public User updateUser(User inUser) {
		User outUser = userRepository.getUserById(inUser.getId());
		if (outUser != null) {
			outUser.setName(inUser.getName());
			outUser.setEmail(inUser.getEmail());
			outUser.setPassword(inUser.getPassword());
			outUser.setRole(inUser.getRole());
			outUser.setActive(inUser.getActive());
			userRepository.save(outUser);
			BeanUtils.copyProperties(outUser, inUser);
			return inUser;
		} else {
			return null;
		}
	}

	@Override
	public int deleteUser(Long id) {
		try {
			User outUser = userRepository.getUserById(id);
			if (outUser == null) {
				return -1;
			} else {
				userRepository.delete(outUser.getId());
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public User updatePartial(User user, long id) {
		User outUser = userRepository.getUserById(id);
		if (outUser != null) {
			outUser.setEmail(user.getEmail());
			userRepository.save(outUser);
			BeanUtils.copyProperties(outUser, user);
			return user;
		} else {
			return null;
		}
	}

}
