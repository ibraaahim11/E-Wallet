package com.vois.e_wallet.services;

import com.vois.e_wallet.entities.Transaction;
import com.vois.e_wallet.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService implements GenericService<Transaction, String> {
	private final TransactionRepository TransactionRepository;


	@Override
	public Transaction save(Transaction Transaction) {
		return TransactionRepository.save(Transaction);
	}


	@Override
	public Optional<Transaction> findById(String id) {
		return TransactionRepository.findById(id);
	}

	@Override
	public List<Transaction> findAll() {
		return TransactionRepository.findAll();
	}

	@Override
	public Transaction update(String id, Transaction Transaction) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		TransactionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Transaction found with Id " + id));

		return TransactionRepository.save(Transaction);


	}

	@Override
	public void deleteById(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}
		TransactionRepository.deleteById(id);

	}


}




