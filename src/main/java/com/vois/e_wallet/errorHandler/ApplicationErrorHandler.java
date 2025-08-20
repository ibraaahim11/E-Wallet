package com.vois.e_wallet.errorHandler;

import com.vois.e_wallet.dto.CustomError;
import com.vois.e_wallet.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationErrorHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<CustomError> handleUserNotFoundException(UserNotFoundException ex) {
    CustomError error = CustomError.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<CustomError> handleWalletNotFoundException(WalletNotFoundException ex) {
        CustomError error = CustomError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserHasNoWalletException.class)
    public ResponseEntity<CustomError> handleUserHasNoWalletException(UserHasNoWalletException ex) {
        CustomError error = CustomError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<CustomError> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        CustomError error = CustomError.builder()
                .status(HttpStatus.PAYMENT_REQUIRED.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.PAYMENT_REQUIRED);
    }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<CustomError> handleRunTimeException(RuntimeException ex) {
    CustomError error = CustomError.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("Internal server error. See logs for details.")
        .build();
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
