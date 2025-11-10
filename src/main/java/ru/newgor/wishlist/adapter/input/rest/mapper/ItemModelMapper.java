package ru.newgor.wishlist.adapter.input.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.newgor.wishlist.adapter.input.api.dto.GetItems200Response;
import ru.newgor.wishlist.adapter.input.api.dto.Item;
import ru.newgor.wishlist.domain.ItemBaseInfo;
import ru.newgor.wishlist.domain.ItemModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemModelMapper {

    ItemModel toItemModel(Item item);

    Item toItemDto(ItemModel item);

    @Mapping(target = "_list", source = "items")
    GetItems200Response toGetItems200Response(List<ItemBaseInfo> items);
}
