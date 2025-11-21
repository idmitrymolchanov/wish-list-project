package ru.newgor.wishlist.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {

    public UserAlreadyExistsException(String login) {
        super(HttpStatus.CONFLICT, String.format("User with login %s already exists", login));
    }
}
