package com.vois.e_wallet.repository;

import com.vois.e_wallet.entities.Transaction;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.enums.TransactionType;
import com.vois.e_wallet.repositories.TransactionRepository;
import com.vois.e_wallet.repositories.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TransactionRepositoryTest {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private WalletRepository walletRepository;

	@Test
	void saveAndFindTransactionTest() {
		// Given
		Wallet sender = Wallet.builder().balance(100f).build();
		Wallet receiver = Wallet.builder().balance(50f).build();
		walletRepository.save(sender);
		walletRepository.save(receiver);

		Transaction tx = Transaction.builder()
				.fromWallet(sender)
				.toWallet(receiver)
				.amount(25f)
				.timestamp(LocalDateTime.now())
				.type(TransactionType.TRANSFER)
				.build();

		// When
		Transaction savedT = transactionRepository.save(tx);

		// Then
		assertNotNull(savedT.getId());
		Optional<Transaction> found = transactionRepository.findById(savedT.getId());
		assertTrue(found.isPresent());
		assertEquals(25f, found.get().getAmount());
	}

	@Test
	void deleteTransactionTest() {
		Wallet sender = walletRepository.save(Wallet.builder().balance(200f).build());
		Wallet receiver = walletRepository.save(Wallet.builder().balance(150f).build());

		Transaction tx = transactionRepository.save(
				Transaction.builder()
						.fromWallet(sender)
						.toWallet(receiver)
						.amount(50f)
						.timestamp(LocalDateTime.now())
						.type(TransactionType.DEPOSIT)
						.build()
		);

		transactionRepository.delete(tx);

		Optional<Transaction> deleted = transactionRepository.findById(tx.getId());
		assertFalse(deleted.isPresent());
	}
}
