/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucnm.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import phucnm.store.StoreDTO;

/**
 *
 * @author Minh Phuc
 */
public class CartObject implements Serializable {

    private Map<String, StoreDTO> items;

    public CartObject(Map<String, StoreDTO> items) {
        this.items = items;
    }

    public CartObject() {
    }

    /**
     * @return the items
     */
    public Map<String, StoreDTO> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Map<String, StoreDTO> items) {
        this.items = items;
    }

    public int addItemToCart(StoreDTO dto) {
        //1. Checking existed Cart container
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //Cart is existed
        //2. Checking existed item
        int quantity = 1;
        if (this.items.containsKey(dto.getId())) {
            quantity = this.items.get(dto.getId()).getQuantity() + 1;

        }//end item is existed
        //3. Update Cart
        this.items.put(dto.getId(), dto);

        return quantity;
    }
    

    public int updatePrice(StoreDTO dto) {
        //1. Checking existed Cart container
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //Cart is existed
        //2. Checking existed item
        int price = dto.getMoney();
        if (this.items.containsKey(dto.getId())) {

            price = this.items.get(dto.getId()).getMoney() + dto.getMoney();
        }//end item is existed
        //3. Update Cart
        this.items.put(dto.getId(), dto);

        return price;
    }

    public void removeItemFromCart(String itemName) {
        //1. Checking existed Cart Container
        if (this.items == null) {
            return;
        }
        //Cart is existed 
        //2. Checking existed items
        if (this.items.containsKey(itemName)) {
            this.items.remove(itemName);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

//    public void addItemToCart(String itemName) {
//        //1. Checking Existed Cart container
//        if (this.getItems() == null) {
//            this.setItems(new HashMap<>());
//        }
//        //Cart is exsited
//        //2. Checking existed Item
//        int quantity = 1;
//        if (this.getItems().containsKey(itemName)) {
//            quantity = this.getItems().get(itemName) + 1;
//        }//end items
//        //3. Update Cart(Bo hang vao GIO)
//        this.getItems().put(itemName, quantity);
//
//    }
//    public void removeItemFromCart(String itemName) {
//        //1. Checking existed Cart container
//        if (this.getItems() == null) {
//            return;
//        }
//        if (this.getItems().containsKey(itemName)) {
//            this.getItems().remove(itemName);
//            if (this.getItems().isEmpty()) {
//                this.setItems(null);
//            }
//        }// end item is existed
//
//    }
}
