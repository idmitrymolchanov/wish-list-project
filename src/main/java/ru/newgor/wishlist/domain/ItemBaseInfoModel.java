package ru.newgor.wishlist.domain;

import java.time.Instant;
import java.util.UUID;

public record ItemBaseInfoModel(
        UUID id,
        String name,
        String amount,
        boolean reserved,
        Instant createDate
) {
}
