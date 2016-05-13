package com.deniel.system.repository;

import com.deniel.system.domain.Order;

import java.util.List;

/**
 * Created by Deniel on 22.04.2016.
 */
public interface IOrderRepository extends ICrud<String, Order> {

    List<Order> readAll();
}
