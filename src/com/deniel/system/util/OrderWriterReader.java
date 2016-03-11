package com.deniel.system.util;

import com.deniel.system.domain.Order;

import java.io.*;

/**
 * Created by Deniel on 11.03.2016.
 */
public class OrderWriterReader {

    public void write(Order order) throws IOException {
        File dir = new File("orders");
        dir.mkdir();
        File writer = new File("orders/demo.dat");
        ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(writer));
        try {
            ostream.writeObject(order);
        } catch (IOException e) {
            System.err.println(e);
        }
        finally {
            ostream.close();
        }
    }

    public void reader() throws IOException {
        File reader = new File("orders/demo.dat");
        try (ObjectInputStream istream = new ObjectInputStream(new FileInputStream(reader))) {
            Order unknown = (Order) istream.readObject();
            System.out.println(unknown);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
            System.err.println("IO Error");
        }
    }
}