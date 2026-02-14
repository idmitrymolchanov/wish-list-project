package ru.newgor.wishlist.adapter.output.persistence.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.newgor.wishlist.adapter.output.persistence.item.entity.ItemEntity;
import ru.newgor.wishlist.domain.ItemBaseInfoModel;
import ru.newgor.wishlist.domain.ItemModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "statusCode", constant = "OPEN")
    ItemEntity toItemEntity(ItemModel itemModel);

    ItemModel toItemModel(ItemEntity itemEntity);

    @Mapping(target = ".", source = "itemEntity")
    @Mapping(target = "reserved", source = "itemEntity.reserved", conditionExpression = "java(showReservedStatus)")
    ItemBaseInfoModel toItemBaseInfo(ItemEntity itemEntity, boolean showReservedStatus);

    default List<ItemBaseInfoModel> toItemBaseInfoList(List<ItemEntity> itemEntities, boolean showReservedStatus) {
        return itemEntities.stream().map(item -> toItemBaseInfo(item, showReservedStatus)).toList();
    }
}
