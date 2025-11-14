package ru.newgor.wishlist.adapter.input.rest.item;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.newgor.wishlist.adapter.input.api.ItemsApi;
import ru.newgor.wishlist.adapter.input.api.dto.CreateItem200Response;
import ru.newgor.wishlist.adapter.input.api.dto.GetItems200Response;
import ru.newgor.wishlist.adapter.input.api.dto.Item;
import ru.newgor.wishlist.adapter.input.api.dto.ItemUpdate;
import ru.newgor.wishlist.adapter.input.api.dto.SortField;
import ru.newgor.wishlist.adapter.input.rest.item.mapper.ItemModelMapper;
import ru.newgor.wishlist.usecase.port.input.ItemInputPort;
import ru.newgor.wishlist.usecase.service.JwtService;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ItemController implements ItemsApi {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final ItemInputPort inputPort;
    private final ItemModelMapper mapper;
    private final HttpServletRequest request;
    private final JwtService jwtService;

    @Override
    public CreateItem200Response createItem(Item item) {
        var login = jwtService.isTokenValid(getAuthToken());

        var request = mapper.toItemModel(item);
        var savedItemId = inputPort.createItem(request, login);

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
    public GetItems200Response getItems(String userLogin, String statusCode, OffsetDateTime createDateFrom, OffsetDateTime createDateTo, Integer limit, Integer offset, Boolean showReservedStatus, SortField sortField) {
        var response = inputPort.getItems(userLogin, statusCode, createDateFrom, createDateTo, limit, offset, showReservedStatus);
        return mapper.toGetItems200Response(response);
    }

    @Override
    public void patchItem(UUID id, ItemUpdate itemUpdate) {
        var request = mapper.toItemUpdateModel(itemUpdate);
        inputPort.patchItem(id, request);
    }

    @Override
    public void reserveItem(UUID id, Boolean reserved) {
        inputPort.reserveItem(id, reserved);
    }

    @Override
    public void setItemStatus(UUID id, String statusCode) {
        inputPort.setItemStatus(id, statusCode);
    }

    @PostMapping(path = "/lal")
    public ResponseEntity<CreateItem200Response> createItem() {

        var response = new CreateItem200Response();
        response.setId(UUID.randomUUID());

        return ResponseEntity.ok(response);
    }

    private String getAuthToken() {
        return request.getHeader(AUTHORIZATION_HEADER);
    }
}
