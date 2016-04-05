package com.deniel.system.util;

import com.deniel.system.domain.Order;
import com.deniel.system.ui.OrderAction;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Deniel on 11.03.2016.
 */
public class OrderWriterReader {
    private static final String FILE_NAME = "orders/demo.dat";

    public void writeFile(Order order) {

        File dir = new File("orders");
        dir.mkdir();
        File collectionFile = new File(FILE_NAME);
        List<Order> collection = (!collectionFile.exists()) ? new ArrayList<Order>() : readAll();
        collection.add(order);
        sortCollection(collection);
        writeStream(collectionFile, collection);
    }


    public void writeStream(File writer, List<Order> collection) {
        try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(writer))) {
            ostream.writeObject(collection);
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    public List<Order> readAll() {
        File collectionFile = new File(FILE_NAME);
        List<Order> returnList = new ArrayList<>();
        try (ObjectInputStream istream = new ObjectInputStream(new FileInputStream(collectionFile))) {
            returnList = (List<Order>) istream.readObject();
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("IO Error");
        }
        return returnList;
    }


    public List<Order> sortCollection(List<Order> list) {
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

    public Order getOrderById(String ID) throws IOException {
        List<Order> collection = readAll();
        Order order = new Order();
        for (Order pair : collection) {
            if (pair.getId().equals(ID)) {
                order = pair;
            }
        }
        return order;
    }
}



