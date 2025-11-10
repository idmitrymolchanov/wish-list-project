package ru.newgor.wishlist.adapter.output.persistence.item.mapper;

import org.mapstruct.Mapper;
import ru.newgor.wishlist.adapter.output.persistence.item.entity.ItemEntity;
import ru.newgor.wishlist.domain.ItemBaseInfo;
import ru.newgor.wishlist.domain.ItemModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemEntity toItemEntity(ItemModel itemModel);

    ItemModel toItemModel(ItemEntity itemEntity);

    List<ItemBaseInfo> toItemBaseInfoList(List<ItemEntity> itemEntities);
}
