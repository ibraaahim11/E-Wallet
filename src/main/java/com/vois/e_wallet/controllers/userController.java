package com.vois.e_wallet.controllers;

import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class userController {
	private final UserService userService;


	@GetMapping
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	@PostMapping
	public void addUser(@RequestBody User user)
	{
		userService.addUser(user);

	}
}
