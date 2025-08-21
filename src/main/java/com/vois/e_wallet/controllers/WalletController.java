package com.vois.e_wallet.controllers;

import com.vois.e_wallet.dto.WalletDTO;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.services.WalletService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallets")
@AllArgsConstructor
public class WalletController {
	private final WalletService WalletService;


	@GetMapping
	public ResponseEntity<List<WalletDTO> > findAllWallets() {
		return new ResponseEntity<>(WalletService.findAll(), HttpStatus.OK) ;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<WalletDTO>>  findWalletById(@PathVariable String id) {
        return new ResponseEntity<>(WalletService.findById(id), HttpStatus.OK) ;
	}

	@PostMapping
	public ResponseEntity< WalletDTO>  saveWallet(@Valid @RequestBody WalletDTO w) {
        return new ResponseEntity<>(WalletService.save(w), HttpStatus.CREATED) ;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  deleteWallet(@PathVariable String id) {
		WalletService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<WalletDTO>  updateWallet(@PathVariable String id, @Valid @RequestBody WalletDTO w) {
        return new ResponseEntity<>(WalletService.update(id, w), HttpStatus.OK) ;
	}

}
