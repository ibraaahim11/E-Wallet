package com.vois.e_wallet.services;

import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletService implements GenericService<Wallet, String> {
	private final WalletRepository WalletRepository;


	@Override
	public Wallet save(Wallet Wallet) {
		return WalletRepository.save(Wallet);
	}


	@Override
	public Optional<Wallet> findById(String id) {
		return WalletRepository.findById(id);
	}

	@Override
	public List<Wallet> findAll() {
		return WalletRepository.findAll();
	}

	@Override
	public Wallet update(String id, Wallet Wallet) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		WalletRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Wallet found with Id " + id));

		return WalletRepository.save(Wallet);


	}

	@Override
	public void deleteById(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}
		WalletRepository.deleteById(id);

	}


}




