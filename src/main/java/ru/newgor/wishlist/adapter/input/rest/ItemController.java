package ru.newgor.wishlist.adapter.input.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.newgor.wishlist.adapter.input.api.ItemsApi;
import ru.newgor.wishlist.adapter.input.api.dto.CreateItem200Response;
import ru.newgor.wishlist.adapter.input.api.dto.GetItems200Response;
import ru.newgor.wishlist.adapter.input.api.dto.Item;
import ru.newgor.wishlist.adapter.input.api.dto.ItemUpdate;
import ru.newgor.wishlist.adapter.input.rest.mapper.ItemModelMapper;
import ru.newgor.wishlist.usecase.port.input.ItemInputPort;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ItemController implements ItemsApi {

    private final ItemInputPort inputPort;
    private final ItemModelMapper mapper;

    @Override
    public CreateItem200Response createItem(Item item) {
        var request = mapper.toItemModel(item);
        var savedItemId = inputPort.createItem(request);

        var response = new CreateItem200Response();
        response.setId(savedItemId);

        return response;
    }

    @Override
    public Item getItemById(UUID id) {
        var response = inputPort.getItemById(id);
        return mapper.toItemDto(response);
    }

    @Override
    public GetItems200Response getItems(String statusCode, OffsetDateTime createDateFrom, OffsetDateTime createDateTo, Integer limit, Integer offset, Boolean showReservedStatus) {
        var response = inputPort.getItems(statusCode, createDateFrom, createDateTo, limit, offset, showReservedStatus);
        return mapper.toGetItems200Response(response);
    }

    @Override
    public void patchItem(UUID id, ItemUpdate itemUpdate) {

    }

    @Override
    public void reserveItem(Boolean reserved) {

    }

    @Override
    public void setItemStatus(String statusCode) {

    }
}
