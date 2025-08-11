package com.vois.e_wallet.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name="WALLETS")
@Data
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private Float balance;

	@OneToOne(mappedBy = "wallet")
	@JsonBackReference
	private User user;

	@OneToMany(mappedBy = "fromWallet")
	private List<Transaction> sentTransactions;

	@OneToMany(mappedBy = "toWallet")
	private List<Transaction> receivedTransactions;




}
