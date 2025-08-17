package com.vois.e_wallet.dto;

import com.vois.e_wallet.enums.TransactionType;
import lombok.Data;

@Data
public class TransactionAction {

    private String fromUserId;
    private String toUserId;
    private Float amount;
    private TransactionType type;

}
