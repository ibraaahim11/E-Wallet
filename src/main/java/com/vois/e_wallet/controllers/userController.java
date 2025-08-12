package com.vois.e_wallet.controllers;

import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class userController {
	private final UserService userService;


	@GetMapping
	public List<User> findAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<User> findUserById(@PathVariable String id) {
		return userService.findById(id);
	}

	@PostMapping
	public User saveUser(@RequestBody User user) {
		return userService.save(user);

	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteById(id);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable String id, @RequestBody User user) {
		return userService.update(id, user);
	}

}
