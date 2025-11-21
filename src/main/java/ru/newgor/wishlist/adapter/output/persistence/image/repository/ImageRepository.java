package ru.newgor.wishlist.adapter.output.persistence.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.newgor.wishlist.adapter.output.persistence.image.entity.ImageEntity;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {
}
