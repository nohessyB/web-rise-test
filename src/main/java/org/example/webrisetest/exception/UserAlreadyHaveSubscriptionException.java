package org.example.webrisetest.exception;

public class UserAlreadyHaveSubscriptionException extends RuntimeException {
    public UserAlreadyHaveSubscriptionException(String message) {
        super(message);
    }
}
