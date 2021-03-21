package com.todlist.todlist;

import com.todlist.todlist.model.Item;
import com.todlist.todlist.model.ItemStatus;
import com.todlist.todlist.repositories.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testCreateOfItem() {
        Item item = new Item("itemName", "itemDescription", ItemStatus.NOT_STARTED);
        Item dbItem = itemRepository.save(item);
        System.out.println("======= id is " + dbItem.getId());
        assertNotNull(dbItem.getId());
    }

    @Test
    public void testItemFindById() {
        Item item = new Item("itemName", "itemDescription", ItemStatus.NOT_STARTED);
        Item dbItem = itemRepository.save(item);
        assertNotNull(itemRepository.findById(dbItem.getId()));
    }

    @Test
    public void testUpdateOfItem() {
        Item item = new Item("itemName", "itemDescription", ItemStatus.NOT_STARTED);
        Item dbItem = itemRepository.save(item);
        dbItem.setItemStatus(ItemStatus.IN_PROGRESS);
        Item updatedItem = itemRepository.save(dbItem);
        assertEquals(ItemStatus.IN_PROGRESS, updatedItem.getItemStatus());
    }

    @Test
    public void testDeleteItem() {
        Item item = new Item("itemName", "itemDescription", ItemStatus.NOT_STARTED);
        Item dbItem = itemRepository.save(item);
        itemRepository.deleteById(dbItem.getId());
        assertEquals(0, itemRepository.count());
    }

    @Test
    public void testFindAllItems() {
        Item item = new Item("itemName", "itemDescription", ItemStatus.NOT_STARTED);
        Item item2 = new Item("itemName2", "itemDescription2", ItemStatus.NOT_STARTED);
        itemRepository.save(item);
        itemRepository.save(item2);
        assertEquals(2, itemRepository.count());
    }

    @Test
    public void testFindAllItemsWithStatusDone() {
        Item item = new Item("itemName", "itemDescription", ItemStatus.NOT_STARTED);
        Item item2 = new Item("itemName2", "itemDescription2", ItemStatus.DONE);
        Item item3 = new Item("itemName3", "itemDescription3", ItemStatus.DONE);
        itemRepository.save(item);
        itemRepository.save(item2);
        itemRepository.save(item3);
        assertEquals(2, itemRepository.findAllByItemStatus(ItemStatus.DONE).size());
    }
}
