package com.vois.e_wallet.errorHandler;

import com.vois.e_wallet.dto.CustomError;
import com.vois.e_wallet.entities.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		CustomError error = CustomError.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.message(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage())
				.build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<CustomError> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		String cause = ex.getMostSpecificCause().getMessage().toLowerCase();

		CustomError error = CustomError.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.build();


		if (cause.contains("users") && cause.contains("username")) {

			error.setMessage("Username is already taken.");


		} else {
			error.setMessage("Database error occurred.");
		}
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


	}

	//  @ExceptionHandler(RuntimeException.class)
//  public ResponseEntity<CustomError> handleRunTimeException(RuntimeException ex) {
//    CustomError error = CustomError.builder()
//        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//        .message("Internal server error. See logs for details.")
//        .build();
//    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//  }
}