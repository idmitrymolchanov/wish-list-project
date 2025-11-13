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
    @Mapping(target = "currency", source = "currency")
    ItemEntity toItemEntity(ItemModel itemModel);

    ItemModel toItemModel(ItemEntity itemEntity);

    List<ItemBaseInfoModel> toItemBaseInfoList(List<ItemEntity> itemEntities);
}
