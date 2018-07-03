package com.company.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.company.rest.entity.User;
import com.company.rest.service.UserService;

@RestController
@RequestMapping(value={"/user"})
public class UserController {
	
	@Autowired
	UserService userService;
	
//	@RequestMapping("/")
//	public String index() {
//		return "Welcome Spring Boot";
//	}

//	@PostMapping("/save")
//	public User save(@RequestBody User user) {
//		return userService.saveUser(user);
//	}
	
	@GetMapping("/get")
	public ResponseEntity<Object> getAll() {
		return  ResponseEntity.ok().body(userService.getAllUsers());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
	    User user = userService.getUserById(id);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<User>> getUserByName(@PathVariable(value="name") String name) {
			List<User> users = userService.findByUserName(name);
			if (users == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok().body(users);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Void> save(@RequestBody User user, UriComponentsBuilder builder) {
		User outUser = userService.getUserById(user.getId());
		if (outUser != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		userService.saveUser(user);	 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/save/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id, @RequestBody User inUser) {
		inUser.setId(id);
		User user = userService.updateUser(inUser);
	    if(user == null) {
	        return ResponseEntity.ok().body(new String("Not Found"));
	    }
	    return ResponseEntity.ok().body(user);
	}
	
	@PatchMapping("/partial/{id}")
	public ResponseEntity<Object> updateUserPartial(@PathVariable("id") Long id, @RequestBody User inUser) {
		User user = userService.getUserById(id);
	    if(user == null) {
	        return ResponseEntity.ok().body(new String("Not Found"));
	    }
	    User userOut = userService.updatePartial(inUser, id);
	    return ResponseEntity.ok().body(userOut);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value ="id") Long id) {		
		int  user = userService.deleteUser(id);
	    if(user<=0) {
	        return ResponseEntity.ok().body(new String("Not Found"));
	    }
	    return ResponseEntity.ok().body(new String("Deleted SuccessFully"));
	}
}

