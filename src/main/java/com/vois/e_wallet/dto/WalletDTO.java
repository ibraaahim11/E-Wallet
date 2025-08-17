package com.vois.e_wallet.dto;

import com.vois.e_wallet.entities.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDTO {
	private String id;
	private Float balance;
	private String userId;


    public WalletDTO(Wallet wallet)
    {
        this.id = wallet.getId();
        this.balance = wallet.getBalance();
        this.userId = wallet.getUser().getId();
    }
}
