package com.vois.e_wallet.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vois.e_wallet.dto.WalletDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="WALLETS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private Float balance;

	@OneToOne(mappedBy = "wallet")
	private User user;

	@OneToMany(mappedBy = "fromWallet")
	@Builder.Default


	private List<Transaction> sentTransactions =  new ArrayList<>();;

	@OneToMany(mappedBy = "toWallet")
	@Builder.Default


	private List<Transaction> receivedTransactions = new ArrayList<>();

	public Wallet(WalletDTO walletDTO) {
		if (walletDTO != null) {
			this.id = walletDTO.getId();
			this.balance = walletDTO.getBalance();
		}
	}





}
