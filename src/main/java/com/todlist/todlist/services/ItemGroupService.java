package com.todlist.todlist.services;

import com.todlist.todlist.model.ItemGroup;

import java.util.List;

public interface ItemGroupService {
    ItemGroup save(ItemGroup itemGroup);
    void delete(Long id);
    List<ItemGroup> findAll();
    ItemGroup findById(Long id);
}
