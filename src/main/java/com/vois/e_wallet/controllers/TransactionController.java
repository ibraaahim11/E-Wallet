package com.vois.e_wallet.controllers;

import com.vois.e_wallet.dto.*;
import com.vois.e_wallet.entities.Transaction;
import com.vois.e_wallet.services.TransactionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor

public class TransactionController {
	private final TransactionService transactionService;



    @GetMapping
	public List<TransactionDTO> findAllTransactions() {
		return transactionService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<TransactionDTO> findTransactionById(@PathVariable String id) {
		return transactionService.findById(id);
	}

	@PostMapping
	public TransactionDTO saveTransaction(@RequestBody Transaction t) {
		return transactionService.save(t);

	}

	@DeleteMapping("/{id}")
	public void deleteTransaction(@PathVariable String id) {
		transactionService.deleteById(id);
	}

	@PutMapping("/{id}")
	public TransactionDTO updateTransaction(@PathVariable String id, @RequestBody Transaction t) {
		return transactionService.update(id, t);
	}

	@PostMapping("/deposit")
	public TransactionDTO deposit(@RequestBody TransactionAction ta)
	{

		return transactionService.makeTransaction(ta);
	}

	@PostMapping("/withdraw")
	public TransactionDTO withdraw(@RequestBody TransactionAction ta)
	{

        return transactionService.makeTransaction(ta);
	}
	@PostMapping("/transfer")
	public TransactionDTO transfer(@RequestBody TransactionAction ta)
	{

        return transactionService.makeTransaction(ta);
	}

}
