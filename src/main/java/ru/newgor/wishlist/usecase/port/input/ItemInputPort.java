package ru.newgor.wishlist.usecase.port.input;

import ru.newgor.wishlist.domain.ItemBaseInfo;
import ru.newgor.wishlist.domain.ItemModel;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface ItemInputPort {

    UUID createItem(ItemModel item);

    ItemModel getItemById(UUID id);

    List<ItemBaseInfo> getItems(String statusCode, OffsetDateTime createDateFrom, OffsetDateTime createDateTo, Integer limit, Integer offset, Boolean showReservedStatus);

}
