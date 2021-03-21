package com.todlist.todlist.services;

import com.todlist.todlist.exceptions.ResourceNotFoundException;
import com.todlist.todlist.model.Item;
import com.todlist.todlist.model.ItemStatus;
import com.todlist.todlist.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// Prin annotarea de service spunem spring-ului ce impleemntarea sa foloseasca pentru ItemService
//
//
// |                                                                                         |
// |   ApplicationCotnext                                                                    |
// |______Interfata ItemService -- am implemntarea ItemServiceImplementation_________________|
@Service
public class ItemServiceImplementation implements ItemService{

    private ItemRepository itemRepository;

    public ItemServiceImplementation(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            return optionalItem.get();
        } else {
            throw new ResourceNotFoundException(String.format("Resource item with id %d was not found", id));
        }
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> findAllItemsByStatus(ItemStatus itemStatus) {
        return itemRepository.findAllByItemStatus(itemStatus);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
