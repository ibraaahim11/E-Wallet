package com.vois.e_wallet.dto;

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

//	@Builder.Default
//	private List<String> sentTransactionsIds = new ArrayList<>();
//	@Builder.Default
//	private List<String> receivedTransactionsIds = new ArrayList<>();
}
