package com.vois.e_wallet.entities;

import com.vois.e_wallet.enums.TransactionType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="TRANSACTIONS")
@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@ManyToOne
	@JoinColumn(name="from_wallet_id")
	private Wallet fromWallet;

	@ManyToOne
	@JoinColumn(name="to_wallet_id")
	private Wallet toWallet;

	private LocalDateTime timestamp;
	private Float amount;
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	@Nullable
	private String description;



}
