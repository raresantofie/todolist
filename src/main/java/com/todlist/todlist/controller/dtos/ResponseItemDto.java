package com.todlist.todlist.controller.dtos;

import com.todlist.todlist.model.ItemStatus;

public class ResponseItemDto {
    private Long id;
    private String name;
    private String description;
    private Long assignedToItemGroupId;
    private String assignedToItemGroupName;
    private ItemStatus itemStatus;

    public ResponseItemDto() {

    }

    public ResponseItemDto(Long id, String name, String description, ItemStatus itemStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.itemStatus = itemStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignedToItemGroupName() {
        return assignedToItemGroupName;
    }

    public void setAssignedToItemGroupName(String assignedToItemGroupName) {
        this.assignedToItemGroupName = assignedToItemGroupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAssignedToItemGroupId() {
        return assignedToItemGroupId;
    }

    public void setAssignedToItemGroupId(Long assignedToItemGroupId) {
        this.assignedToItemGroupId = assignedToItemGroupId;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }
}
