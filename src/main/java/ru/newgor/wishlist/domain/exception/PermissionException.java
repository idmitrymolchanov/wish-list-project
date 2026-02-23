package ru.newgor.wishlist.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PermissionException extends ResponseStatusException {

    public PermissionException(String login) {
        super(HttpStatus.FORBIDDEN, "user '" + login + "' does not have permission to perform this action");
    }
}
