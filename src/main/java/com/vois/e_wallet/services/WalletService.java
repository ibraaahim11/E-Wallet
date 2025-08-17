package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.WalletDTO;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WalletService implements GenericService<WalletDTO,String,Wallet> {
	private final WalletRepository walletRepository;



    @Override
	public WalletDTO save(Wallet Wallet) {
		return new WalletDTO(walletRepository.save(Wallet));
	}


    @Override
	public Optional<WalletDTO> findById(String id) {
		return walletRepository.findById(id).map(WalletDTO::new);

	}

    @Override
	public List<WalletDTO> findAll() {
		return walletRepository.findAll().stream().map(WalletDTO::new).collect(Collectors.toList());
	}


    @Override
	public WalletDTO update(String id, Wallet Wallet) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		walletRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Wallet found with Id " + id));

        return new WalletDTO(walletRepository.save(Wallet));


	}

    @Override
	public void deleteById(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}
		walletRepository.deleteById(id);

	}

	public Wallet addMoney(String walletId, Float amount) {
		if (walletId == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("No Wallet found with Id " + walletId));

		wallet.setBalance(wallet.getBalance() + amount);
		return walletRepository.save(wallet);



	}

	public Wallet subtractMoney(String walletId, Float amount) {
		if (walletId == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("No Wallet found with Id " + walletId));

		wallet.setBalance(wallet.getBalance() - amount);
		return walletRepository.save(wallet);


	}


}




