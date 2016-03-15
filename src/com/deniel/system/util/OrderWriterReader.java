package com.deniel.system.util;

import com.deniel.system.domain.Order;

import java.io.*;

/**
 * Created by Deniel on 11.03.2016.
 */
public class OrderWriterReader {

    public void write(Order order) {
        File dir = new File("orders");
        dir.mkdir();
        File writer = new File("orders/demo.dat");

        try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(writer))) {
            ostream.writeObject(order);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void reader() {
        File reader = new File("orders/demo.dat");
        try (ObjectInputStream istream = new ObjectInputStream(new FileInputStream(reader))) {
            Order unknown = (Order) istream.readObject();
            System.out.println(unknown);
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("IO Error");
        }
    }
}