package com.todlist.todlist.controller.dtos;

import java.util.List;

public class ResponseItemGroupDto {

    private Long id;
    private String itemGroupName;
    private List<ResponseItemDto> responseItemDtoList;

    public ResponseItemGroupDto() {

    }

    public ResponseItemGroupDto(Long id, String itemGroupName, List<ResponseItemDto> responseItemDtoList) {
        this.id = id;
        this.itemGroupName = itemGroupName;
        this.responseItemDtoList = responseItemDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public List<ResponseItemDto> getResponseItemDtoList() {
        return responseItemDtoList;
    }

    public void setResponseItemDtoList(List<ResponseItemDto> responseItemDtoList) {
        this.responseItemDtoList = responseItemDtoList;
    }
}
