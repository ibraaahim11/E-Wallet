package com.vois.e_wallet.controllers;

import com.vois.e_wallet.dto.WalletDTO;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.services.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallets")
@AllArgsConstructor
public class WalletController {
	private final WalletService WalletService;


	@GetMapping
	public List<WalletDTO> findAllWallets() {
		return WalletService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<WalletDTO> findWalletById(@PathVariable String id) {
		return WalletService.findById(id);
	}

	@PostMapping
	public WalletDTO saveWallet(@RequestBody Wallet Wallet) {
		return WalletService.save(Wallet);

	}

	@DeleteMapping("/{id}")
	public void deleteWallet(@PathVariable String id) {
		WalletService.deleteById(id);
	}

	@PutMapping("/{id}")
	public WalletDTO updateWallet(@PathVariable String id, @RequestBody Wallet Wallet) {
		return WalletService.update(id, Wallet);
	}

}
