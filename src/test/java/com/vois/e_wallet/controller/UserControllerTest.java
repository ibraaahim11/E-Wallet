package com.vois.e_wallet.controller;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vois.e_wallet.controllers.UserController;
import com.vois.e_wallet.dto.UserRegisterDTO;
import com.vois.e_wallet.dto.UserResponseDTO;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void findAllUsers() throws Exception {
		UserResponseDTO u1 = new UserResponseDTO().builder().username("alice").build();
		UserResponseDTO u2 = new UserResponseDTO().builder().username("bob").build();

		List<UserResponseDTO> users = Arrays.asList(u1, u2);
		Mockito.when(userService.findAll()).thenReturn(users);

		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].username").value("alice"))
				.andExpect(jsonPath("$[1].username").value("bob"));
	}

	@Test
	void findUserById() throws Exception {
		String id = "1";
		UserResponseDTO u1 = new UserResponseDTO().builder().username("alice").id(id).build();

		Mockito.when(userService.findById(id)).thenReturn(Optional.of(u1));

		mockMvc.perform(get("/api/users/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("alice"));
	}


}