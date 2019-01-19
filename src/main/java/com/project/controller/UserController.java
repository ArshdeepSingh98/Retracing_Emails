package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.UserService;

import com.project.model.User;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/h")
	public String h() {
		return "hi";
	}
	
	@RequestMapping("/users")
    public List<User> getAllUsers(){
    	return userService.getAllUsers();
    }
	
	@RequestMapping("/users/{email}")
    public User getUserById(@PathVariable String email){
    	return userService.getUser(email);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
    
//    @RequestMapping(method=RequestMethod.PUT, value="/users/{email}")
//	public void updateUser(@RequestBody User user,@PathVariable String email) {
//    	userService.updateUser(user, email);
//	}
//	
//	@RequestMapping(method=RequestMethod.DELETE, value="/users/{email}")
//	public void deleteStudent(@PathVariable String email) {
//		userService.deleteUser(email);
//	}
}
