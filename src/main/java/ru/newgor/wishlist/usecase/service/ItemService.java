package ru.newgor.wishlist.usecase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.newgor.wishlist.adapter.output.persistence.item.ItemPersistence;
import ru.newgor.wishlist.domain.ItemBaseInfo;
import ru.newgor.wishlist.domain.ItemModel;
import ru.newgor.wishlist.usecase.port.input.ItemInputPort;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService implements ItemInputPort {

    private final ItemPersistence persistence; // todo -> outputPort

    @Override
    public UUID createItem(ItemModel item) {
        return persistence.createItem(item);
    }

    @Override
    public ItemModel getItemById(UUID id) {
        return persistence.getItemById(id);
    }

    @Override
    public List<ItemBaseInfo> getItems(String statusCode, OffsetDateTime createDateFrom, OffsetDateTime createDateTo, Integer limit, Integer offset, Boolean showReservedStatus) {
        return persistence.getItems(statusCode, createDateFrom.toInstant(), createDateTo.toInstant(), limit, offset, showReservedStatus);
    }
}
