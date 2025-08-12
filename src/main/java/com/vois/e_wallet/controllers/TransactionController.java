package com.vois.e_wallet.controllers;

import com.vois.e_wallet.dto.Deposit;
import com.vois.e_wallet.dto.TransactionDTO;
import com.vois.e_wallet.dto.Transfer;
import com.vois.e_wallet.dto.Withdrawal;
import com.vois.e_wallet.entities.Transaction;
import com.vois.e_wallet.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;


	@GetMapping
	public List<Transaction> findAllTransactions() {
		return transactionService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Transaction> findTransactionById(@PathVariable String id) {
		return transactionService.findById(id);
	}

	@PostMapping
	public Transaction saveTransaction(@RequestBody Transaction t) {
		return transactionService.save(t);

	}

	@DeleteMapping("/{id}")
	public void deleteTransaction(@PathVariable String id) {
		transactionService.deleteById(id);
	}

	@PutMapping("/{id}")
	public Transaction updateTransaction(@PathVariable String id, @RequestBody Transaction t) {
		return transactionService.update(id, t);
	}

	@PostMapping("/deposit")
	public TransactionDTO deposit(@RequestBody Deposit deposit)
	{

		return transactionService.deposit(deposit);
	}

	@PostMapping("/withdraw")
	public TransactionDTO withdraw(@RequestBody Withdrawal withdrawal)
	{

		return transactionService.withdraw(withdrawal);
	}
	@PostMapping("/transfer")
	public TransactionDTO transfer(@RequestBody Transfer transfer)
	{

		return transactionService.transfer(transfer);
	}

}
