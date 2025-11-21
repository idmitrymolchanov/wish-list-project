package ru.newgor.wishlist.domain.exception;

import java.util.UUID;

public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException(UUID id) {
        super(String.format("image with id '%s' not found", id));
    }
}
