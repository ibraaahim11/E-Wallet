package com.vois.e_wallet.errorHandler;

public class UserHasNoWalletException extends RuntimeException{
    public UserHasNoWalletException(String userId) {
        super("User with id '" +userId + "' has no wallet.");
    }

}
