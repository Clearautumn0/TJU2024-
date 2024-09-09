package com.neusoft.elmboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

	public Map<String, Integer> getUserById(@PathVariable String userId) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", userService.getUserById(userId));
		return response;

	}
	
	//@RequestMapping("/saveUser")
	@PostMapping
	public Map<String, Integer> saveUser(@RequestBody User user) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", userService.saveUser(user));
		return response;
	}
	
	@PutMapping
	public Map<String, Integer> updateUser(@RequestBody User user) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", userService.updateUser(user));
		return response;
	}
	
	@DeleteMapping
	public Map<String, Integer> deleteUser(@RequestBody User user) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", userService.deleteUser(user));
		return response;
	}
	
	@PatchMapping
	public Map<String, Integer> updateAuthorization(@RequestBody User user) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", userService.updateAuthorization(user));
		return response;
	}
}
