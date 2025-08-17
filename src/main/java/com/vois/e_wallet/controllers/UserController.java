package com.vois.e_wallet.controllers;

import com.vois.e_wallet.dto.UserDTO;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;


	@GetMapping
	public List<UserDTO> findAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<UserDTO> findUserById(@PathVariable String id) {
		return userService.findById(id);
	}

	@PostMapping
	public UserDTO saveUser(@RequestBody User user) {
		return userService.save(user);

	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteById(id);
	}

	@PutMapping("/{id}")
	public UserDTO updateUser(@PathVariable String id, @RequestBody User user) {
		return userService.update(id, user);
	}

}
