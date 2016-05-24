package com.deniel.system.repository.sql;

import com.deniel.system.domain.Order;
import com.deniel.system.repository.IOrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deniel on 21.05.2016.
 */
public class OrderRepositorySql implements IOrderRepository {
    private static final String READ_ALL_SQL = "SELECT tm_order_id, name, amount, price FROM tm_order";
    private static final String CREATE_SQL = "INSERT INTO tm_order (name, amount, price, tm_order_id) values(?,?, ?, ?)";
    private static final String READ_SQL = "SELECT tm_order_id, name, amount, price FROM tm_order where (tm_order_id = ?)";
    private static final String UPDATE_SQL = "UPDATE tm_order SET name = ?, amount = ?, price = ? where tm_order_id = ?";
    private static final String DELETE_SQL = "Delete FROM tm_order where tm_order_id = ?";
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/testmakakus";
    private static final String USER = "postgres";
    private static final String PASSWORD = "30051989";
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
            ResultSet resultSet = statement.executeQuery(READ_ALL_SQL);
            while (resultSet.next()) {
                Order order = orderSetter(resultSet);
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
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SQL);
            setPreparedStatement(preparedStatement, order);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order read(String id) {
        Order order = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_SQL);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = orderSetter(resultSet);
            }
            return order;
        } catch (SQLException e) {
            return order;
        }
    }

    @Override
    public void update(Order order) {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
                    setPreparedStatement(preparedStatement, order);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    }

    @Override
    public boolean delete(String id) {
        if (read(id) != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    private void setPreparedStatement (PreparedStatement preparedStatement, Order entity) throws SQLException{
        preparedStatement.setString(1, entity.getOrderName());
        preparedStatement.setInt(2, entity.getAmount());
        preparedStatement.setBigDecimal(3, entity.getPrice());
        preparedStatement.setString(4, entity.getId());
    }

    private Order orderSetter (ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getString("tm_order_id"));
        order.setOrderName(resultSet.getString("name"));
        order.setAmount(resultSet.getInt("amount"));
        order.setPrice(resultSet.getBigDecimal("price"));
        return order;
    }
}