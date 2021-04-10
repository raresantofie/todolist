package com.todlist.todlist.controller.dtos;

import com.todlist.todlist.model.Item;
import com.todlist.todlist.model.ItemStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResponseUtils {

    public static ResponseItemDto mapItemToResponseItemDto(Item item) {
        ResponseItemDto responseItemDto
                = new ResponseItemDto(item.getId(), item.getName(),
                item.getDescription(), item.getItemStatus());
        if (item.getItemGroup() != null) {
            responseItemDto.setAssignedToItemGroupId(item.getItemGroup().getId());
            responseItemDto.setAssignedToItemGroupName(item.getItemGroup().getItemGroupName());
        }
        if (item.getItemStatus().equals(ItemStatus.DONE)) {
            long diffInMilliseconds = item.getEndDate().getTime() - item.getStartDate().getTime();
            long diffInSeconds = TimeUnit.SECONDS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
            responseItemDto.setDuration(String.format("Took %d seconds(s)", diffInSeconds));
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
