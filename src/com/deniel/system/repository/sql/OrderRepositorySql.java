package com.deniel.system.repository.sql;

import com.deniel.system.domain.Order;
import com.deniel.system.repository.IOrderRepository;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deniel on 21.05.2016.
 */
public class OrderRepositorySql implements IOrderRepository {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/testmakakus";
    private static String USER = "postgres";
    private static String PASSWORD = "30051989";
    private Connection connection = null;

    public OrderRepositorySql() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> readAll() {
        List<Order> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tm_order");
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getString(1));
                order.setOrderName(resultSet.getString(4));
                order.setAmount(resultSet.getInt(2));
                order.setPrice(resultSet.getBigDecimal(3));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void create(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tm_order (tm_order_id, name, amount, price) values(?,?, ?, ?)");
            preparedStatement.setString(1, order.getId());
            preparedStatement.setString(2, order.getOrderName());
            preparedStatement.setInt(3, order.getAmount());
            preparedStatement.setBigDecimal(4, order.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order read(String id) {
        ResultSet resultSet = null;
        Order order = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tm_order where (tm_order_id = ?)");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getString(1));
                order.setOrderName(resultSet.getString(4));
                order.setAmount(resultSet.getInt(2));
                order.setPrice(resultSet.getBigDecimal(3));
            }
            return order;
        } catch (SQLException e) {
            return order;
        }
    }

    @Override
    public void update(Order entity) {

        List<Order> collection = readAll();
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId().equals(entity.getId())) {
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = connection.prepareStatement("UPDATE tm_order SET name = ?, amount = ?, price = ? where tm_order_id = ?");
                preparedStatement.setString(1, entity.getOrderName());
                preparedStatement.setInt(2, entity.getAmount());
                preparedStatement.setBigDecimal(3, entity.getPrice());
                    preparedStatement.setString(4, entity.getId());
                preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean delete(String id) {
        ResultSet resultSet = null;
        Order order = new Order();
        boolean deleted = true;
        if (read(id) != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("Delete FROM tm_order where tm_order_id = ?");
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate(); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return deleted;
        } else {
            return false;
        }
    }
}