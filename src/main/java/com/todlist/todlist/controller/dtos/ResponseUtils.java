package com.todlist.todlist.controller.dtos;

import com.todlist.todlist.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ResponseUtils {

    public static ResponseItemDto mapItemToResponseItemDto(Item item) {
        ResponseItemDto responseItemDto
                = new ResponseItemDto(item.getId(), item.getName(),
                item.getDescription(), item.getItemStatus());
        if (item.getItemGroup() != null) {
            responseItemDto.setAssignedToItemGroupId(item.getItemGroup().getId());
            responseItemDto.setAssignedToItemGroupName(item.getItemGroup().getItemGroupName());
        }
        return responseItemDto;
    }

    public static List<ResponseItemDto> mapItemListToResponseItemDtoList(List<Item> items) {
        List<ResponseItemDto> responseItemList = new ArrayList<>();
        items.forEach(item -> {
            ResponseItemDto responseItemDto = mapItemToResponseItemDto(item);
            responseItemList.add(responseItemDto);
        });
        return responseItemList;
    }
}
