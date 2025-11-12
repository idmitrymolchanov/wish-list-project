package ru.newgor.wishlist.adapter.input.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.newgor.wishlist.adapter.input.api.dto.GetItems200Response;
import ru.newgor.wishlist.adapter.input.api.dto.Item;
import ru.newgor.wishlist.adapter.input.api.dto.ItemBaseInfo;
import ru.newgor.wishlist.domain.ItemBaseInfoModel;
import ru.newgor.wishlist.domain.ItemModel;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemModelMapper {

    @Mapping(target = "currency", source = "currency")
    ItemModel toItemModel(Item item);

    Item toItemDto(ItemModel item);

    List<ItemBaseInfo> toItemBaseInfoList(List<ItemBaseInfoModel> item);

    default GetItems200Response toGetItems200Response(List<ItemBaseInfoModel> items) {
        GetItems200Response response = new GetItems200Response();
        response.setList(toItemBaseInfoList(items));
        return response;
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }
}
