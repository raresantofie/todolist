package com.todlist.todlist.services;

import com.todlist.todlist.model.Item;
import com.todlist.todlist.model.ItemStatus;

import java.util.List;

public interface ItemService {
    Item save(Item item);
    Item findById(Long id);
    void delete(Long id);
    List<Item> findAllItemsByStatus(ItemStatus itemStatus);
    List<Item> findAll();
}
