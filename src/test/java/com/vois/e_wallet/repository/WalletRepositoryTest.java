package com.vois.e_wallet.repository;

import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.repositories.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class WalletRepositoryTest {

	@Autowired
	private WalletRepository walletRepository;

	@Test
	void saveAndFindWalletTest() {
		// Given
		Wallet wallet = Wallet.builder()
				.balance(500f)
				.build();

		// When
		Wallet saved = walletRepository.save(wallet);

		// Then
		assertNotNull(saved.getId());
		Optional<Wallet> found = walletRepository.findById(saved.getId());
		assertTrue(found.isPresent());
		assertEquals(500f, found.get().getBalance());
	}

	@Test
	void updateWalletBalanceTest() {
		Wallet wallet = walletRepository.save(Wallet.builder().balance(100f).build());

		wallet.setBalance(250f);
		walletRepository.save(wallet);

		Wallet updated = walletRepository.findById(wallet.getId()).get();
		assertEquals(250f, updated.getBalance());
	}

	@Test
	void deleteWalletTest() {
		Wallet wallet = walletRepository.save(Wallet.builder().balance(300f).build());

		walletRepository.delete(wallet);

		Optional<Wallet> deleted = walletRepository.findById(wallet.getId());
		assertFalse(deleted.isPresent());
	}
}
