package com.vois.e_wallet.service;

import com.vois.e_wallet.dto.UserResponseDTO;
import com.vois.e_wallet.entities.User;


import com.vois.e_wallet.errorHandler.UserNotFoundException;
import com.vois.e_wallet.repositories.UserRepository;
import com.vois.e_wallet.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void returnUserWhenFoundByIdTest() {
        // Given
        User user = User.builder()
                .id("1")
                .username("ibrahim123")
                .build();

        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        // When
        Optional<UserResponseDTO> result = userService.findById("1");

        // Then
        assertNotNull(result);
        assertEquals("ibrahim123", result.get().getUsername());
    }

    @Test
    void throwExceptionWhenUserNotFoundTest() {
        when(userRepository.findById("999")).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userService.findById("999"));
    }
}
