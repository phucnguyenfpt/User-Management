/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucnm.store;

import java.io.Serializable;

/**
 *
 * @author Minh Phuc
 */
public class StoreDTO  implements Serializable{
    private String id;
    private String name;
    private int quantity;
    private int money;

    public StoreDTO() {
    }
    
    public StoreDTO(String id, String name, int money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public StoreDTO(String id, String name, int quantity, int money) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.money = money;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }
    
    
    
    
}
