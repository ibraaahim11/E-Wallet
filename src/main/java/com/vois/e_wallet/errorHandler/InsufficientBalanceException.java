package com.vois.e_wallet.errorHandler;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(Float amount, Float balance) {
        super("Cannot withdraw more than balance amount. Withdrawal: " + amount + ", balance: " + balance);
    }

}
