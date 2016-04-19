package com.deniel.system.domain;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * Created by Deniel on 02.03.2016.
 */
public class Order implements Serializable {
    private String id;
    private String orderName;
    private Integer amount;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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
        return  "\nID: " + id +
                "\nName: " + orderName +
                "\nAmount: " + amount +
                "\nPrice: " + price;
    }
}
