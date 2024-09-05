package com.neusoft.elmboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	//@RequestMapping("/getUserByIdByPass")
	@PostMapping("/login")
	public User getUserByIdByPass(@RequestBody User user) throws Exception{
		return userService.getUserByIdByPass(user);
	}
	
	//@RequestMapping("/getUserById")
	@GetMapping("/{userId}")
	public int getUserById(@PathVariable String userId) throws Exception{
	
		return userService.getUserById(userId);
	}
	
	//@RequestMapping("/saveUser")
	@PostMapping
	public int saveUser(@RequestBody User user) throws Exception{
		return userService.saveUser(user);
	}
	
	@PutMapping
	public int updateUser(@RequestBody User user) throws Exception{
		return userService.updateUser(user);
	}
	
	@DeleteMapping
	public int daleteUser(@RequestBody User user) throws Exception{
		return userService.deleteUser(user);
	}
}
