package ru.newgor.wishlist.usecase.port.input;

import ru.newgor.wishlist.domain.ItemBaseInfoModel;
import ru.newgor.wishlist.domain.ItemModel;
import ru.newgor.wishlist.domain.ItemUpdateModel;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface ItemInputPort {

    UUID createItem(ItemModel item, String userLogin);

    ItemModel getItemById(UUID id);

    List<ItemBaseInfoModel> getItems(String userLogin, String statusCode, OffsetDateTime createDateFrom, OffsetDateTime createDateTo, Integer limit, Integer offset, Boolean showReservedStatus);

    void patchItem(UUID id, ItemUpdateModel itemUpdate);

    void reserveItem(UUID id, Boolean reserved);

    void setItemStatus(UUID id, String statusCode, String userLogin);
}
