package com.vois.e_wallet.controllers;

import com.vois.e_wallet.dto.UserDTO;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;


	@GetMapping
	public ResponseEntity<List<UserDTO>> findAllUsers() {
		return new ResponseEntity<> (userService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<UserDTO>> findUserById(@PathVariable String id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK) ;
	}

	@PostMapping
	public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO u) {
        return new ResponseEntity<>(userService.save(u), HttpStatus.CREATED) ;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @Valid @RequestBody UserDTO u) {
        return new ResponseEntity<>(userService.update(id, u), HttpStatus.OK) ;
	}

}
