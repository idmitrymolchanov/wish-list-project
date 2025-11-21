package ru.newgor.wishlist.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class JwtTokenExpiredException extends ResponseStatusException {

    public JwtTokenExpiredException(String reason) {
        super(HttpStatus.FORBIDDEN, reason);
    }
}
