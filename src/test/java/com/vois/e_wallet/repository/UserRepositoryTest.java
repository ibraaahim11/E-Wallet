package com.vois.e_wallet.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.enums.UserRole;
import com.vois.e_wallet.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void saveTest()
	{
		// Given
		User user = User.builder().username("ibrahim").password("123").role(UserRole.USER).build();

		// When
		User savedUser = userRepository.save(user);

		// Then
		assertNotNull(savedUser.getId());
		assertEquals("ibrahim",savedUser.getUsername());
		assertEquals("123",savedUser.getPassword());
		assertEquals(UserRole.USER, savedUser.getRole());

	}

	@Test
	void deleteUserTest() {
		// Given
		User user = new User();
		user.setEmail("delete@example.com");
		// When
		User saved = userRepository.save(user);
		userRepository.delete(saved);
		// then

		Optional<User> deleted = userRepository.findById(saved.getId());
		assertFalse(deleted.isPresent());
	}

}
