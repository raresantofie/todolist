package com.todlist.todlist.controller.dtos;

import com.todlist.todlist.model.Item;
import com.todlist.todlist.model.ItemGroup;

import java.util.ArrayList;
import java.util.List;

public class RequestUtils {

    public static Item mapItemDtoToItem(RequestItemDto itemDto) {
        return new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getItemStatus());
    }

    /**
     * input : {"itemGroupName": "test", "requestItemDtoList" : [{"name":" "test-1", "description" : "test-desc", "status": "IN_PROGRESS"} }
     * output : { "id" : 1, "itemGroupName": "test", "itemList": [{"id": 1, "name":"test-1", "description": "test-desc", "status": "IN_PROGRESS"}}
     * @param requestItemGroupDto
     * @return
     */
    public static ItemGroup mapItemGroupDtoToItemGroup(RequestItemGroupDto requestItemGroupDto) {
        ItemGroup itemGroup = new ItemGroup();
        List<Item> itemList = new ArrayList<>();
        itemGroup.setItemGroupName(requestItemGroupDto.getItemGroupName());

        requestItemGroupDto
                .getRequestItemDtoList()
                .forEach(requestItemDto -> itemList.add(mapItemDtoToItem(requestItemDto)));
        itemGroup.setItemList(itemList);
        return itemGroup;
    }
}
