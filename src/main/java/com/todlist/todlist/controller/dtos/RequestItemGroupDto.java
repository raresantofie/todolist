package com.todlist.todlist.controller.dtos;

import java.util.List;

public class RequestItemGroupDto {
    private String itemGroupName;
    private List<RequestItemDto> requestItemDtoList;


    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public List<RequestItemDto> getRequestItemDtoList() {
        return requestItemDtoList;
    }

    public void setRequestItemDtoList(List<RequestItemDto> requestItemDtoList) {
        this.requestItemDtoList = requestItemDtoList;
    }
}
