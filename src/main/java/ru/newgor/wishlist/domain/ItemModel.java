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
public class ItemModel {

    private UUID id;
    private String name;
    private Double amount;
    private String currency;
    private String description;
    private String linkToSite;
    private int priority;
    private String priorityName;
    private String image;
    private String statusCode;
    private boolean reserved;
    private Instant createDate;
    private Instant lastUpdateDate;
    private String userLogin;
}
