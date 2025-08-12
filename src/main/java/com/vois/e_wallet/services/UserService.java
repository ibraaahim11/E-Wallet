package com.vois.e_wallet.services;

import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements GenericService<User, String> {
	private final UserRepository userRepository;


	@Override
	public User save(User user) {
		if(user.getWallet() == null)
		{
			Wallet wallet = Wallet.builder().balance((float) 0).build();
			user.setWallet(wallet);
		}




		return userRepository.save(user);
	}


	@Override
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User update(String id, User user) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with Id " + id));

		return userRepository.save(user);


	}

	@Override
	public void deleteById(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}
		userRepository.deleteById(id);

	}


}




