package com.vois.e_wallet.dto;

import com.vois.e_wallet.entities.Wallet;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDTO {
	private String id;
    @NotNull(message = "Wallet should have a balance.")
    @PositiveOrZero(message = "Wallet's balance should be positive or zero.")


	private Float balance;
	private String userId;


	public WalletDTO(Wallet wallet) {
		if (wallet != null) {
			this.id = wallet.getId();
			this.balance = wallet.getBalance();
			this.userId = wallet.getUser().getId();
		}

	}
}
