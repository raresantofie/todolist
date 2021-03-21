package com.todlist.todlist.repositories;

import com.todlist.todlist.model.Item;
import com.todlist.todlist.model.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByItemStatus(ItemStatus itemStatus);

}
