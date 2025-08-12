package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.WalletDTO;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletService  {
	private final WalletRepository walletRepository;



	public Wallet save(Wallet Wallet) {
		return walletRepository.save(Wallet);
	}


	public WalletDTO findById(String id) {
		Optional<Wallet> optionalWallet = walletRepository.findById(id);

		if (optionalWallet.isEmpty()) {
			return null;
		} else {
			Wallet wallet = optionalWallet.get();
			return WalletDTO.builder().id(wallet.getId()).balance(wallet.getBalance()).userId(wallet.getUser().getId()).build();
		}
	}

	public List<Wallet> findAll() {
		return walletRepository.findAll();
	}


	public Wallet update(String id, Wallet Wallet) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		walletRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Wallet found with Id " + id));

		return walletRepository.save(Wallet);


	}

	public void deleteById(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}
		walletRepository.deleteById(id);

	}

	public WalletDTO addMoney(String walletId, Float amount) {
		if (walletId == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("No Wallet found with Id " + walletId));

		wallet.setBalance(wallet.getBalance() + amount);
		Wallet savedWallet = walletRepository.save(wallet);

		return WalletDTO.builder().id(savedWallet.getId()).balance(savedWallet.getBalance()).build();

	}

	public WalletDTO subtractMoney(String walletId, Float amount) {
		if (walletId == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("No Wallet found with Id " + walletId));

		wallet.setBalance(wallet.getBalance() - amount);
		Wallet savedWallet = walletRepository.save(wallet);

		return WalletDTO.builder().id(savedWallet.getId()).balance(savedWallet.getBalance()).userId(savedWallet.getUser().getId()).build();

	}


}




