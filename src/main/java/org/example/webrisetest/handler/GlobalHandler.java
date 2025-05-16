package org.example.webrisetest.handler;

import org.example.webrisetest.exception.SubscriptionNotFoundException;
import org.example.webrisetest.exception.UserAlreadyHaveSubscriptionException;
import org.example.webrisetest.exception.UserNotFoundException;
import org.example.webrisetest.exception.UserSubscriptionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserException(UserNotFoundException userException) {
        return new ResponseEntity<>(userException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    public ResponseEntity<String> handleSubscriptionException(SubscriptionNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserSubscriptionNotFoundException.class)
    public ResponseEntity<String> handleSubscriptionException(UserSubscriptionNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyHaveSubscriptionException.class)
    public ResponseEntity<String> handleSubscriptionException(UserAlreadyHaveSubscriptionException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
