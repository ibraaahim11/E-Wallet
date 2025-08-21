package com.vois.e_wallet.entities;

import com.vois.e_wallet.dto.TransactionDTO;
import com.vois.e_wallet.enums.TransactionType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="TRANSACTIONS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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


	public Transaction(TransactionDTO transactionDTO)
	{
		if(transactionDTO != null) {
			this.id = transactionDTO.getId();
			this.timestamp = transactionDTO.getTimestamp();
			this.amount = transactionDTO.getAmount();
			this.type = transactionDTO.getType();
		}
	}


}
