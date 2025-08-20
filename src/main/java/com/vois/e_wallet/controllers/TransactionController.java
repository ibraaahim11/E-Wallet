package com.vois.e_wallet.controllers;

import com.vois.e_wallet.dto.*;
import com.vois.e_wallet.entities.Transaction;
import com.vois.e_wallet.services.TransactionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor

public class TransactionController {
	private final TransactionService transactionService;



    @GetMapping
	public ResponseEntity<List<TransactionDTO>> findAllTransactions() {
		return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK) ;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<TransactionDTO>> findTransactionById(@PathVariable String id) {
		return new ResponseEntity<>(transactionService.findById(id),HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TransactionDTO> saveTransaction(@RequestBody Transaction t) {
		return new ResponseEntity<>(transactionService.save(t), HttpStatus.CREATED) ;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransaction(@PathVariable String id) {
		transactionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TransactionDTO>  updateTransaction(@PathVariable String id, @RequestBody Transaction t) {
		return new ResponseEntity<>(transactionService.update(id, t),HttpStatus.OK) ;
	}

	@PostMapping("/deposit")
	public ResponseEntity<TransactionDTO>  deposit(@RequestBody TransactionAction ta)
	{

        return new ResponseEntity<>(transactionService.makeTransaction(ta),HttpStatus.CREATED) ;
	}

	@PostMapping("/withdraw")
	public ResponseEntity<TransactionDTO>  withdraw(@RequestBody TransactionAction ta)
	{

        return new ResponseEntity<>(transactionService.makeTransaction(ta),HttpStatus.CREATED) ;
	}
	@PostMapping("/transfer")
	public ResponseEntity<TransactionDTO>  transfer(@RequestBody TransactionAction ta)
	{

        return new ResponseEntity<>(transactionService.makeTransaction(ta),HttpStatus.CREATED) ;
	}

}
