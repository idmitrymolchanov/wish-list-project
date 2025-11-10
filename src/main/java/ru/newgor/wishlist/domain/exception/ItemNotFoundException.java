package ru.newgor.wishlist.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class ItemNotFoundException extends ResponseStatusException {

    public ItemNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, String.format("item not found with id %s", id));
    }
}
