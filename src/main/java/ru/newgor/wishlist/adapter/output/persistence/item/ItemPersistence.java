package ru.newgor.wishlist.adapter.output.persistence.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.newgor.wishlist.adapter.output.persistence.item.entity.ItemEntity;
import ru.newgor.wishlist.adapter.output.persistence.item.mapper.ItemMapper;
import ru.newgor.wishlist.adapter.output.persistence.item.repository.ItemRepository;
import ru.newgor.wishlist.adapter.output.persistence.item.repository.specification.ItemSpecification;
import ru.newgor.wishlist.domain.ItemBaseInfoModel;
import ru.newgor.wishlist.domain.ItemModel;
import ru.newgor.wishlist.domain.exception.ItemNotFoundException;

import java.time.Instant;
import java.util.List;
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

    public List<ItemBaseInfoModel> getItems(String userLogin, String statusCode, Instant createDateFrom, Instant createDateTo, Integer limit, Integer offset, Boolean showReservedStatus) {
        var spec = ItemSpecification.filterByParams(userLogin, statusCode, createDateFrom, createDateTo);
        var pageable = ItemSpecification.getPageable(limit, offset);
        var itemEntityPage = repository.findAll(spec, pageable);

        return mapper.toItemBaseInfoList(itemEntityPage.getContent());
    }

    public void reserveItem(UUID id, Boolean reserved) {
        var itemEntity = findEntityById(id);
        itemEntity.setReserved(reserved);
        repository.save(itemEntity);
    }

    public void setItemStatus(UUID id, String statusCode) {
        var itemEntity = findEntityById(id);
        itemEntity.setStatusCode(statusCode);
        repository.save(itemEntity);
    }

    private ItemEntity findEntityById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }
}
