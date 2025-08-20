package com.vois.e_wallet.errorHandler;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userId) {
        super("User with id '" +userId + "' not found.");
    }

}
