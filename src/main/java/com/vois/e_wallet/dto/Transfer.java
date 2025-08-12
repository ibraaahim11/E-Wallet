package com.vois.e_wallet.dto;

import lombok.Data;

@Data
public class Transfer {
	private String fromUserId;
	private String toUserId;
	private Float amount;
}
