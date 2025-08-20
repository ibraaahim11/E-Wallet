package com.vois.e_wallet.dto;

import com.vois.e_wallet.entities.Transaction;
import com.vois.e_wallet.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
	private String id;
	private String fromWalletId;
	private String toWalletId;
	private LocalDateTime timestamp;
    @NotNull(message = "Transaction amount should be present.")
    @PositiveOrZero(message = "Transaction amount should be positive or zero.")

    private Float amount;
    @NotNull(message = "Transaction type should be present.")

    private TransactionType type;

    public TransactionDTO(Transaction transaction)
    {

        this.id = transaction.getId();
        this.fromWalletId = transaction.getFromWallet() != null ? transaction.getFromWallet().getId() : null;
        this.toWalletId = transaction.getToWallet() != null ? transaction.getToWallet().getId() : null;
        this.timestamp = transaction.getTimestamp();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();

    }



}


