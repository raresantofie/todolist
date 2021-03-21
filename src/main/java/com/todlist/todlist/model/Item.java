package com.todlist.todlist.model;

import javax.persistence.*;
import java.util.Date;

/**
 *
 *
 *  {
 *      "id": null,
 *      "name": "test-name",
 *      "description": "test-descriptioon",
 *      "status": "test-status",
 *      "createdDate1" : null,
 *      "createdDate2" : null,
 *
 *
 *  }
 *
 *
 *
 */
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private ItemStatus itemStatus;

    @ManyToOne
    @JoinColumn(name = "itemGroup_id")
    private ItemGroup itemGroup;

    public Item() {
    }

    public Item(String name, String description, ItemStatus itemStatus) {
        this.name = name;
        this.description = description;
        this.itemStatus = itemStatus;
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }
}
