package com.vois.e_wallet.service;

import com.vois.e_wallet.dto.WalletDTO;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.repositories.WalletRepository;
import com.vois.e_wallet.services.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

	@Mock
	private WalletRepository walletRepository;
	@InjectMocks
	private WalletService walletService;

	@Test
	void saveAndReturnWalletTest() {
		Wallet wallet = Wallet.builder()
				.id("w1")
				.balance(500f)
				.sentTransactions(new ArrayList<>())
				.receivedTransactions(new ArrayList<>())
				.build();
		when(walletRepository.save(wallet)).thenReturn(wallet);

		WalletDTO result = walletService.save(new WalletDTO(wallet));

		assertEquals(500f, result.getBalance());
	}

	@Test
	void findWalletByIdTest() {
		Wallet wallet = Wallet.builder().id("w2").balance(1000f).build();
		when(walletRepository.findById("w2")).thenReturn(Optional.of(wallet));

		Optional<WalletDTO> result = walletService.findById("w2");

		assertNotNull(result);
		assertEquals(1000f, result.get().getBalance());
	}
}
