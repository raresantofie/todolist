package com.todlist.todlist.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

///
///
/// Grocery - Chipsuri, Bere, Hamberugeri
/// Facturi - Internet, Curent, Gaz
///
@Entity
public class ItemGroup {

    @Id
    @GeneratedValue
    private Long id;

    private String itemGroupName;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "item_id")
    private List<Item> itemList = new ArrayList<>();

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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        // pentru fiecare item din lista, il anuntam ca este asignat la grupul curent
        itemList
                .forEach(item -> item.setItemGroup(this));
        this.itemList = itemList;
    }
}
