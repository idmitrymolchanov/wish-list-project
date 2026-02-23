package ru.newgor.wishlist.adapter.output.persistence.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.newgor.wishlist.adapter.output.persistence.item.entity.ItemEntity;
import ru.newgor.wishlist.adapter.output.persistence.item.mapper.ItemMapper;
import ru.newgor.wishlist.adapter.output.persistence.item.repository.ItemRepository;
import ru.newgor.wishlist.adapter.output.persistence.item.repository.specification.ItemSpecification;
import ru.newgor.wishlist.domain.ItemBaseInfoModel;
import ru.newgor.wishlist.domain.ItemModel;
import ru.newgor.wishlist.domain.ItemUpdateModel;
import ru.newgor.wishlist.domain.exception.ItemNotFoundException;
import ru.newgor.wishlist.domain.exception.PermissionException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemPersistence {

    private final ItemMapper mapper;
    private final ItemRepository repository;

    public UUID createItem(ItemModel item, String userLogin) {
        var itemEntity = mapper.toItemEntity(item);
        itemEntity.setUserLogin(userLogin);
        return repository.save(itemEntity).getId();
    }

    public ItemModel getItemById(UUID id) {
        var itemEntity = findEntityById(id);
        return mapper.toItemModel(itemEntity);
    }

    public List<ItemBaseInfoModel> getItems(String userLogin, String statusCode, Instant createDateFrom, Instant createDateTo, Integer limit, Integer offset, Boolean showReservedStatus, String sort) {
        var spec = ItemSpecification.filterByParams(userLogin, statusCode, createDateFrom, createDateTo);
        var pageable = ItemSpecification.getPageable(limit, offset, sort);
        var itemEntityPage = repository.findAll(spec, pageable);

        return mapper.toItemBaseInfoList(itemEntityPage.getContent(), showReservedStatus);
    }

    public void reserveItem(UUID id, Boolean reserved) {
        var itemEntity = findEntityById(id);
        itemEntity.setReserved(reserved);
        repository.save(itemEntity);
    }

    public void setItemStatus(UUID id, String statusCode, String userLogin) {
        var itemEntity = findEntityById(id);
        if(!itemEntity.getUserLogin().equals(userLogin)) {
            throw new PermissionException(userLogin);
        }
        itemEntity.setStatusCode(statusCode);
        repository.save(itemEntity);
    }

    public void updateItem(UUID id, ItemUpdateModel itemUpdate, String userLogin) {
        var itemEntity = findEntityById(id);
        if(!itemEntity.getUserLogin().equals(userLogin)) {
            throw new PermissionException(userLogin);
        }

        Optional.ofNullable(itemUpdate.getLinkToSite()).ifPresent(itemEntity::setLinkToSite);
        Optional.ofNullable(itemUpdate.getAmount()).ifPresent(itemEntity::setAmount);
        Optional.ofNullable(itemUpdate.getPriority()).ifPresent(p -> itemEntity.setPriority(Integer.parseInt(p)));
        Optional.ofNullable(itemUpdate.getCurrency()).ifPresent(itemEntity::setCurrency);
        Optional.ofNullable(itemUpdate.getDescription()).ifPresent(itemEntity::setDescription);
        Optional.ofNullable(itemUpdate.getName()).ifPresent(itemEntity::setName);

        repository.save(itemEntity);
    }

    public void deleteItem(UUID id, String userLogin) {
        var itemEntity = findEntityById(id);
        if(!itemEntity.getUserLogin().equals(userLogin)) {
            throw new PermissionException(userLogin);
        }
        repository.deleteById(id);
    }

    private ItemEntity findEntityById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }
}
