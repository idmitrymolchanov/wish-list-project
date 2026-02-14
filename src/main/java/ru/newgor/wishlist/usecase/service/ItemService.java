package ru.newgor.wishlist.usecase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.newgor.wishlist.adapter.output.persistence.item.ItemPersistence;
import ru.newgor.wishlist.domain.ItemBaseInfoModel;
import ru.newgor.wishlist.domain.ItemModel;
import ru.newgor.wishlist.domain.ItemUpdateModel;
import ru.newgor.wishlist.usecase.port.input.ItemInputPort;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService implements ItemInputPort {

    private final ItemPersistence persistence; // todo -> outputPort

    @Override
    public UUID createItem(ItemModel item, String userLogin) {
        item.setPriorityName(definePriorityName(item.getPriority()));
        return persistence.createItem(item, userLogin);
    }

    @Override
    public ItemModel getItemById(UUID id) {
        return persistence.getItemById(id);
    }

    @Override
    public List<ItemBaseInfoModel> getItems(String userLogin, String statusCode, OffsetDateTime createDateFrom, OffsetDateTime createDateTo, Integer limit, Integer offset, Boolean showReservedStatus) {
        return persistence.getItems(userLogin, statusCode, createDateFrom == null ? null : createDateFrom.toInstant(), createDateTo == null ? null : createDateTo.toInstant(), limit, offset, showReservedStatus);
    }

    @Override
    public void patchItem(UUID id, ItemUpdateModel itemUpdate) {

    }

    @Override
    public void reserveItem(UUID id, Boolean reserved) {
        persistence.reserveItem(id, reserved);
    }

    @Override
    public void setItemStatus(UUID id, String statusCode, String userLogin) {
        persistence.setItemStatus(id, statusCode, userLogin);
    }

    private String definePriorityName(int priority) {
        if(priority >= 0 && priority < 4) {
            return "low";
        }
        if(priority >= 4 && priority < 8) {
            return "medium";
        }
        if(priority >= 8 && priority < 11) {
            return "high";
        }
        return "undefined";
    }
}
