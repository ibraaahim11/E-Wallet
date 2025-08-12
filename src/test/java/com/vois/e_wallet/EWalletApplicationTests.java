package com.vois.e_wallet;

import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EWalletApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
		User user = new User();
		user.setFName("ibrahim");
		user.setLName("hesham");

		User savedUser = userService.save(user);

		Assertions.assertNotNull(savedUser);

		Assertions.assertEquals(user.getFName(), savedUser.getFName());

	}

}
