package ru.newgor.wishlist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateModel {

    private UUID id;
    private Instant createDate;
    private Instant lastUpdateDate;
    private String name;
    private Double amount;
    private Boolean reserved;
    private String priority;
    private String priorityName;
    private String currency;
    private String description;
    private String linkToSite;
    private String image;
}
