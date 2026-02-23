package ru.newgor.wishlist.domain;

import java.time.Instant;
import java.util.UUID;

public record ItemBaseInfoModel(
        UUID id,
        String name,
        String amount,
        String currency,
        boolean reserved,
        Instant createDate,
        int priority,
        String priorityName
) {
}
