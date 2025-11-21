package ru.newgor.wishlist.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongPasswordException extends ResponseStatusException {

    public WrongPasswordException(String login) {
        super(HttpStatus.UNAUTHORIZED, String.format("Wrong password for login '%s'", login));
    }
}
