package com.deniel.system.util;

import com.deniel.system.domain.Order;

import java.io.*;
import java.util.*;

/**
 * Created by Deniel on 11.03.2016.
 */
public class OrderWriterReader {
    private static final String FILE_NAME = "orders/demo.dat";
    private static final File COLLECTION_FILE = new File(FILE_NAME);

    public void create(Order order) {
        File dir = new File("orders");
        dir.mkdir();
        List<Order> collection = (!COLLECTION_FILE.exists()) ? new ArrayList<Order>() : readAll();
        if (COLLECTION_FILE.exists() && exist(order)) {
            System.out.println("Order already exist!");
            return;
        }
        collection.add(order);
        sortCollection(collection);
        writeStream(collection);
    }

    public Order read(String ID) {
        List<Order> collection = readAll();
        Order order = new Order();
        for (Order pair : collection) {
            if (pair.getId().equals(ID)) {
                order = pair;
            }
        }
        return order;
    }

    public void update(String ID, Order order) {
        List<Order> collection = readAll();
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId().equals(ID)) {
                collection.set(i, order);
            }
        }
        writeStream(collection);
    }

    public boolean delete(String ID) {
        List<Order> collection = readAll();
        Order order = new Order();
        boolean deleted = false;
        for (Order pair : collection) {
            if (pair.getId().equals(ID)) {
                order = pair;
                deleted = true;
                break;
            }
        }
        collection.remove(order);
        writeStream(collection);
        return deleted;
    }

    public List<Order> readAll() {
        List<Order> returnList = new ArrayList<>();
        try (ObjectInputStream istream = new ObjectInputStream(new FileInputStream(COLLECTION_FILE))) {
            returnList = (List<Order>) istream.readObject();
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("IO Error");
        }
        return returnList;
    }

    private void writeStream(List<Order> collection) {
        try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            ostream.writeObject(collection);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private List<Order> sortCollection(List<Order> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(j).getId().compareTo(list.get(i).getId()) > 0) {
                    Order tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
        return list;
    }

    private boolean exist(Order order) {
        String newOrderId = order.getId();
        Order isExist = read(newOrderId);
        return newOrderId.equals(isExist.getId());
    }
}



