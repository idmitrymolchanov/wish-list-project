package ru.newgor.wishlist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.newgor.wishlist.domain.enums.ItemStatusEnum;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {

    private UUID id;
    private String name;
    private String amount;
    private String currency;
    private String description;
    private String linkToSite;
    private String priority;
    private String image;
    private ItemStatusEnum status;
    private boolean reserved;
    private Instant createDate;
    private Instant lastUpdateDate;
}
