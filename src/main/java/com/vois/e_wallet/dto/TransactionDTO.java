package com.vois.e_wallet.dto;

import com.vois.e_wallet.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
	private String id;
	private String fromWalletId;
	private String toWalletId;
	private LocalDateTime timestamp;
	private Float amount;
	private TransactionType type;
}
