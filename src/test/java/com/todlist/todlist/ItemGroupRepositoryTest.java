package com.todlist.todlist;

import com.todlist.todlist.exceptions.ResourceNotFoundException;
import com.todlist.todlist.model.Item;
import com.todlist.todlist.model.ItemGroup;
import com.todlist.todlist.model.ItemStatus;
import com.todlist.todlist.repositories.ItemGroupRepository;
import com.todlist.todlist.repositories.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemGroupRepositoryTest {

    @Autowired
    private ItemGroupRepository itemGroupRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testCreateItemGroupRepository() {
        Item item = new Item("item_1", "item_description", ItemStatus.DONE);
        Item item2 = new Item("item_2", "item_description2", ItemStatus.IN_PROGRESS);
        ItemGroup itemGroup = new ItemGroup();
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item2);
        itemGroup.setItemGroupName("Item_group_name");
        itemGroup.setItemList(itemList);
        ItemGroup savedItemGroup = itemGroupRepository.save(itemGroup);

        assertNotNull(savedItemGroup.getId());
        assertEquals("Item_group_name", savedItemGroup.getItemGroupName());
        assertEquals(itemList.size(), savedItemGroup.getItemList().size());
    }

    @Test
    public void testUpdateItemFromItemGroup() {
        Item item1 = new Item("item_1", "item_description", ItemStatus.IN_PROGRESS);
        Item item2 = new Item("item_2", "item_description2", ItemStatus.IN_PROGRESS);
        ItemGroup itemGroup = new ItemGroup();
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemGroup.setItemGroupName("Item_group_name");
        itemGroup.setItemList(itemList);
        ItemGroup savedItemGroup = itemGroupRepository.save(itemGroup);

        savedItemGroup
                .getItemList()
                .forEach(item -> {
                    item.setItemStatus(ItemStatus.DONE);
                    itemRepository.save(item);
                });

        ItemGroup itemGroupFromDb = itemGroupRepository.findById(savedItemGroup.getId()).get();
        itemGroupFromDb
                .getItemList()
                .forEach(item -> {
                    assertEquals(ItemStatus.DONE, item.getItemStatus());
                });
    }

    @Test
    public void deleteItemGroup() {
        Item item1 = new Item("item_1", "item_description", ItemStatus.IN_PROGRESS);
        Item item2 = new Item("item_2", "item_description2", ItemStatus.IN_PROGRESS);
        ItemGroup itemGroup = new ItemGroup();
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemGroup.setItemGroupName("Item_group_name");
        itemGroup.setItemList(itemList);
        ItemGroup savedItemGroup = itemGroupRepository.save(itemGroup);

        itemGroupRepository.delete(savedItemGroup);

        Optional<ItemGroup> deletedItemGroup = itemGroupRepository.findById(savedItemGroup.getId());
        // Optional - daca obiectul exista => isPresent == true | daca obiectul nu exista => isPresent = false;
        assertFalse(deletedItemGroup.isPresent());
        long itemRepositoryCount = itemRepository.count(); // numaram item-urile din DB
        assertEquals(0, itemRepositoryCount);
    }
}
