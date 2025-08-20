package com.vois.e_wallet.errorHandler;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException(String walletId) {
        super("Wallet with id '" +walletId + "' not found.");
    }

}
