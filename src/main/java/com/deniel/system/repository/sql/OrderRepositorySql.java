package com.deniel.system.repository.sql;

import com.deniel.system.PropertyValuesGetter;
import com.deniel.system.TmSystemException;
import com.deniel.system.domain.Order;
import com.deniel.system.repository.IOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deniel on 21.05.2016.
 */
public class OrderRepositorySql implements IOrderRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRepositorySql.class);

    private static final String READ_ALL_SQL = "SELECT tm_order_id, name, amount, price FROM tm_order";
    private static final String CREATE_SQL = "INSERT INTO tm_order (name, amount, price, tm_order_id) values(?,?, ?, ?)";
    private static final String READ_SQL = "SELECT tm_order_id, name, amount, price FROM tm_order where (tm_order_id = ?)";
    private static final String UPDATE_SQL = "UPDATE tm_order SET name = ?, amount = ?, price = ? where tm_order_id = ?";
    private static final String DELETE_SQL = "Delete FROM tm_order where tm_order_id = ?";

    private String URL = null;
    private String USER = null;
    private String PASSWORD = null;
    private Connection connection = null;

    public OrderRepositorySql() {
        PropertyValuesGetter getPropertyValues = new PropertyValuesGetter();
        URL = getPropertyValues.getDbURL() + getPropertyValues.getDbName();
        USER = getPropertyValues.getDbUserName();
        PASSWORD = getPropertyValues.getDbPass();
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            LOGGER.error("Database init connection error.", e);
            throw new TmSystemException("Database init connection error.", e);
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
            LOGGER.warn("SQL exception", e);
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
            LOGGER.warn("SQL exception", e);
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
            LOGGER.warn("SQL exception", e);
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
            LOGGER.warn("SQL exception", e);
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
                LOGGER.warn("SQL exception");
            }
            return true;
        } else {
            return false;
        }
    }

    private void setPreparedStatement(PreparedStatement preparedStatement, Order entity) throws SQLException {
        preparedStatement.setString(1, entity.getOrderName());
        preparedStatement.setInt(2, entity.getAmount());
        preparedStatement.setBigDecimal(3, entity.getPrice());
        preparedStatement.setString(4, entity.getId());
    }

    private Order orderSetter(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getString("tm_order_id"));
        order.setOrderName(resultSet.getString("name"));
        order.setAmount(resultSet.getInt("amount"));
        order.setPrice(resultSet.getBigDecimal("price"));
        return order;
    }
}
