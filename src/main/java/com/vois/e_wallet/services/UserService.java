package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.UserDTO;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.repositories.UserRepository;
import com.vois.e_wallet.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements GenericService<UserDTO, String,User> {
	private final UserRepository userRepository;
    private final WalletRepository walletRepository;



    @Override
	public UserDTO save(User user) {

		if(user.getWallet() == null)
		{
            user.setWallet(Wallet.builder().balance(BigDecimal.ZERO.floatValue()).user(user).build());

        }




		return new UserDTO(userRepository.save(user));
	}


	@Override
	public Optional<UserDTO> findById(String id) {
		return userRepository.findById(id).map(UserDTO::new);
	}

	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
	}

	@Override
	public UserDTO update(String id, User user) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with Id " + id));

		return new UserDTO(userRepository.save(user));


	}

	@Override
	public void deleteById(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}
		userRepository.deleteById(id);

	}




}




