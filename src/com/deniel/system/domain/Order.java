package com.deniel.system.domain;

import java.math.BigDecimal;
import java.util.UUID;
import java.io.Serializable;

/**
 * Created by Deniel on 02.03.2016.
 */
public class Order implements Serializable {
    private String id;
    private String orderName;
    private int amount;
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override

    public String toString () {
        return "ID: " + id +
                "\nName: " + orderName +
                "\nAmount: " + amount +
                "\nPrice: " + price;
    }
}
