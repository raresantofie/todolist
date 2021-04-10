package com.todlist.todlist.controller;

import com.todlist.todlist.controller.dtos.RequestItemDto;
import com.todlist.todlist.controller.dtos.RequestUtils;
import com.todlist.todlist.controller.dtos.ResponseItemDto;
import com.todlist.todlist.controller.dtos.ResponseUtils;
import com.todlist.todlist.model.Item;
import com.todlist.todlist.model.ItemStatus;
import com.todlist.todlist.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(path = "/items")
    public ResponseItemDto create(@RequestBody RequestItemDto itemDto) {
        Item itemToBeSaved = RequestUtils.mapItemDtoToItem(itemDto);
        return ResponseUtils.mapItemToResponseItemDto(itemService.save(itemToBeSaved));
    }

    @GetMapping(path = "/items")
    public List<ResponseItemDto> getAll() {
        return ResponseUtils.mapItemListToResponseItemDtoList(itemService.findAll());
    }

    // http://localhost:8080/items/5
    @GetMapping(path = "/items/{id}")
    public ResponseItemDto findItemById(@PathVariable("id") Long id) {
        return ResponseUtils.mapItemToResponseItemDto(itemService.findById(id));
    }

    @PutMapping(path = "/items/{id}")
    public ResponseItemDto updateItem(@PathVariable("id") Long id, @RequestBody RequestItemDto requestItemDto) {
        Item updatedItem = updateItemFromRequestItemDto(itemService.findById(id), requestItemDto); // se updateaza obiectul
        return ResponseUtils.mapItemToResponseItemDto(itemService.save(updatedItem)); // se salveaza obiectul updatat in baza de date si se intoarce inapoi in postman / ui
    }

    @DeleteMapping(path = "/items/{id}")
    public HttpStatus deleteItem(@PathVariable("id") Long id) {
        itemService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping(path = "/items/search")
    public List<ResponseItemDto> searchItems(@RequestParam("itemStatus") ItemStatus itemStatus) {
        return ResponseUtils.mapItemListToResponseItemDtoList(itemService.findAllItemsByStatus(itemStatus));
    }

    /**
     * @param dbItem         - Obiectul din baza de date pe care urmeaza sa il updatam
     * @param requestItemDto - Obiectul din Postman / UI care contine field-urile pe care vrem sa le updatam
     * @return obiectul din baza de date cu noile valori
     */
    private Item updateItemFromRequestItemDto(Item dbItem, RequestItemDto requestItemDto) {
        if (requestItemDto.getName() != null) {
            dbItem.setName(requestItemDto.getName());
        }
        if (requestItemDto.getDescription() != null) {
            dbItem.setDescription(requestItemDto.getDescription());
        }
        if (requestItemDto.getItemStatus() != null) {
            dbItem.setItemStatus(requestItemDto.getItemStatus());
            if (requestItemDto.getItemStatus().equals(ItemStatus.NOT_STARTED)) {
                dbItem.setStartDate(null);
                dbItem.setEndDate(null);
            }
            if (requestItemDto.getItemStatus().equals(ItemStatus.IN_PROGRESS)) {
                // mutam task-ul in progress
                // din greseala il mutam in done
                // din done, vrem sa il mutam inapoi in progress
                if (dbItem.getStartDate() == null) {
                    dbItem.setStartDate(new Date());
                }
                dbItem.setEndDate(null);
            }
            if (requestItemDto.getItemStatus().equals(ItemStatus.DONE)) {
                dbItem.setEndDate(new Date());
            }
        }
        return dbItem;
    }
}
