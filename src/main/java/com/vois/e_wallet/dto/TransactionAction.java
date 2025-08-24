package com.vois.e_wallet.dto;

import com.vois.e_wallet.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionAction {


    private String fromUserId;
    private String toUserId;
    @NotNull(message = "Transaction amount must be specified.")
    @Positive(message = "Transaction amount must be greater than 0.")
    private Float amount;
    @NotNull(message = "Transaction type should be present.")
    private TransactionType type;

}
