package ru.newgor.wishlist.adapter.output.persistence.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.newgor.wishlist.adapter.output.persistence.item.entity.ItemEntity;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, UUID>, JpaSpecificationExecutor<ItemEntity> {
}
