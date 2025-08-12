package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.Deposit;
import com.vois.e_wallet.dto.TransactionDTO;
import com.vois.e_wallet.dto.Transfer;
import com.vois.e_wallet.dto.Withdrawal;
import com.vois.e_wallet.entities.Transaction;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;

import com.vois.e_wallet.enums.TransactionType;
import com.vois.e_wallet.repositories.TransactionRepository;


import com.vois.e_wallet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService implements GenericService<Transaction, String> {
	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;
	private final WalletService walletService;


	@Override
	public Transaction save(Transaction Transaction) {
		return transactionRepository.save(Transaction);
	}


	@Override
	public Optional<Transaction> findById(String id) {
		return transactionRepository.findById(id);
	}

	@Override
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	@Override
	public Transaction update(String id, Transaction Transaction) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		transactionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Transaction found with Id " + id));

		return transactionRepository.save(Transaction);


	}

	@Override
	public void deleteById(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}
		transactionRepository.deleteById(id);

	}


	public TransactionDTO deposit(Deposit deposit) {
		String toUserId = deposit.getUserId();
		Float amount = deposit.getAmount();
		Optional<User> toUser = userRepository.findById(toUserId);

		if (toUser.isEmpty()) {
			throw new IllegalArgumentException("No user with id: " + toUserId);
		}

		Wallet toWallet = toUser.get().getWallet();
		if (toWallet == null) {
			throw new IllegalArgumentException("User with id " + toUserId + " does not have a wallet.");
		}


		Transaction t = Transaction.builder()
				.toWallet(toWallet)
				.fromWallet(null)
				.type(TransactionType.DEPOSIT)
				.amount(amount)
				.timestamp(LocalDateTime.now())
				.build();


		Transaction savedT = transactionRepository.save(t);

		walletService.addMoney(toUser.get().getWallet().getId(), amount);


		return TransactionDTO.builder()
				.id(savedT.getId())
				.fromWalletId(savedT.getFromWallet() != null ? savedT.getFromWallet().getId() : null)
				.toWalletId(savedT.getToWallet() != null ? savedT.getToWallet().getId() : null)
				.timestamp(savedT.getTimestamp())
				.amount(savedT.getAmount())
				.type(savedT.getType())
				.build();


	}


	public TransactionDTO withdraw(Withdrawal withdrawal) {
		String fromUserId = withdrawal.getUserId();
		Float amount = withdrawal.getAmount();
		Optional<User> toUser = userRepository.findById(fromUserId);

		if (toUser.isEmpty()) {
			throw new IllegalArgumentException("No user with id: " + fromUserId);
		}

		Wallet fromWallet = toUser.get().getWallet();
		if (fromWallet == null) {
			throw new IllegalArgumentException("User with id " + fromUserId + " does not have a wallet.");
		}

		if (amount > fromWallet.getBalance()) {
			throw new IllegalArgumentException("Cannot withdraw more than balance amount. Withdrawal: " + withdrawal + ", balance: " + fromWallet.getBalance());

		}


		Transaction t = Transaction.builder()
				.toWallet(null)
				.fromWallet(fromWallet)
				.type(TransactionType.WITHDRAWAL)
				.amount(amount)
				.timestamp(LocalDateTime.now())
				.build();


		Transaction savedT = transactionRepository.save(t);

		walletService.subtractMoney(toUser.get().getWallet().getId(), amount);


		return TransactionDTO.builder()
				.id(savedT.getId())
				.fromWalletId(savedT.getFromWallet() != null ? savedT.getFromWallet().getId() : null)
				.toWalletId(savedT.getToWallet() != null ? savedT.getToWallet().getId() : null)
				.timestamp(savedT.getTimestamp())
				.amount(savedT.getAmount())
				.type(savedT.getType())
				.build();


	}

	public TransactionDTO transfer(Transfer transfer) {

		String toUserId = transfer.getToUserId();
		String fromUserId = transfer.getFromUserId();

		Float amount = transfer.getAmount();
		Optional<User> toUser = userRepository.findById(toUserId);
		Optional<User> fromUser = userRepository.findById(fromUserId);


		if (toUser.isEmpty()) {
			throw new IllegalArgumentException("No user with id: " + toUserId);
		} else if (fromUser.isEmpty()) {
			throw new IllegalArgumentException("No user with id: " + fromUserId);
		}

		Wallet fromWallet = fromUser.get().getWallet();
		Wallet toWallet = toUser.get().getWallet();


		if (fromWallet == null) {
			throw new IllegalArgumentException("User with id " + fromUserId + " does not have a wallet.");
		} else if (toWallet == null) {
			throw new IllegalArgumentException("User with id " + toUserId + " does not have a wallet.");
		}

		if (amount > fromWallet.getBalance()) {
			throw new IllegalArgumentException("Cannot withdraw more than balance amount. Withdrawal: " + amount + ", balance: " + fromWallet.getBalance());

		}


		Transaction t = Transaction.builder()
				.toWallet(toWallet)
				.fromWallet(fromWallet)
				.type(TransactionType.TRANSFER)
				.amount(amount)
				.timestamp(LocalDateTime.now())
				.build();


		Transaction savedT = transactionRepository.save(t);

		walletService.subtractMoney(fromUser.get().getWallet().getId(), amount);
		walletService.addMoney(toUser.get().getWallet().getId(), amount);


		return TransactionDTO.builder()
				.id(savedT.getId())
				.fromWalletId(savedT.getFromWallet() != null ? savedT.getFromWallet().getId() : null)
				.toWalletId(savedT.getToWallet() != null ? savedT.getToWallet().getId() : null)
				.timestamp(savedT.getTimestamp())
				.amount(savedT.getAmount())
				.type(savedT.getType())
				.build();

	}


}




