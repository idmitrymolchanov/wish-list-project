package ru.newgor.wishlist.adapter.output.persistence.image.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {

    @Id
    private UUID id;
    private String name;
    private String path;
    private UUID itemId;
}
