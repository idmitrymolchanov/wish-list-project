package ru.newgor.wishlist.adapter.output.persistence.item.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.newgor.wishlist.domain.enums.ItemStatusEnum;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "document")
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity extends AuditableEntity {

    @Id
    private UUID id;

    private String name;
    private String amount;
    private String currency;
    private String description;
    @Column(name = "link_to_site")
    private String linkToSite;
    private String priority;
    private String image;
    private ItemStatusEnum status;
    private boolean reserved;
}
