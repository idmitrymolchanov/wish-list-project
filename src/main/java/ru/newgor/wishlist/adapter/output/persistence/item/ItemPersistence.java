package ru.newgor.wishlist.adapter.output.persistence.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public UUID createItem(ItemModel item) {
        System.out.println("++++ " + item.getCurrency());
        var itemEntity = mapper.toItemEntity(item);
        System.out.println("++++ " + itemEntity.getCurrency());
        return repository.saveAndFlush(itemEntity).getId();
    }

    public ItemModel getItemById(UUID id) {
        var itemEntity = repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        return mapper.toItemModel(itemEntity);
    }

    public List<ItemBaseInfoModel> getItems(String statusCode, Instant createDateFrom, Instant createDateTo, Integer limit, Integer offset, Boolean showReservedStatus) {
        System.out.println("kmknknknnkkn");
        var spec = ItemSpecification.filterByParams(statusCode, createDateFrom, createDateTo);
        var pageable = ItemSpecification.getPageable(limit, offset);
        var itemEntityPage = repository.findAll(spec, pageable);

        System.out.println(itemEntityPage.getContent().size());

        return mapper.toItemBaseInfoList(itemEntityPage.getContent());
    }
}
