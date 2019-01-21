package com.project.controller;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping("/get-text")
	public @ResponseBody String getText() {
		return "Working Directory = " + System.getProperty("user.dir");

	}

	@GetMapping(value = "/apple.jpg", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(@RequestParam String email, @RequestParam String cid) throws IOException{
		InputStream inputStream = this.getClass().getResourceAsStream("/images/TImage.jpg");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
    	Date date = new Date();
    	String dateString = formatter.format(date);
    	String timeString = formatter2.format(date);
    	User user = new User(email, cid, dateString, timeString);
    	userService.addUser(user);
		return IOUtils.toByteArray(inputStream);
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
