package com.vois.e_wallet.controllers;

import com.vois.e_wallet.dto.UserRegisterDTO;
import com.vois.e_wallet.dto.UserResponseDTO;
import com.vois.e_wallet.services.UserService;
import jakarta.validation.Valid;
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
	public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
		return new ResponseEntity<> (userService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<UserResponseDTO>> findUserById(@PathVariable String id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK) ;
	}

	@PostMapping
	public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody UserRegisterDTO u) {
        return new ResponseEntity<>(userService.save(u), HttpStatus.CREATED) ;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String id, @Valid @RequestBody UserRegisterDTO u) {
        return new ResponseEntity<>(userService.update(id, u), HttpStatus.OK) ;
	}

}
