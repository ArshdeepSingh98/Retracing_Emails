package com.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.User;
import com.project.repository.UserRespository;

@Service
public class UserService {
	
	@Autowired
	private UserRespository userRespository;
	
	private List<User> users = new ArrayList<>(Arrays.asList(new User("email", "cid", "date", "time")));
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User getUser(String email) {
		return users.stream().filter(t -> t.getEmail().equals(email)).findFirst().get();
	}
	
	public void addUser(User user) {
		users.add(user);
		userRespository.save(user);
	}
	
//	public void updateUser(User user, String email) {
//		for(int i=0;i<users.size();i++) {
//			 User t = users.get(i);
//			 if(t.getEmail().equals(email)) {
//				  users.set(i, user);
//				  userRespository.deleteById(t.getId());
//				  userRespository.save(user);
//				  return;
//			 }
//		 }
//	}
//	
//	public void deleteUser(String email) {
//		users.removeIf(t -> t.getEmail().equals(email)); 
//		userRespository.deleteById(email);
//	}
}
