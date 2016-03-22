package com.deniel.system.util;

import com.deniel.system.domain.Order;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by Deniel on 11.03.2016.
 */
public class OrderWriterReader {
    private static final Logger LOGGER = Logger.getLogger(OrderWriterReader.class);

    public void write(Order order) {
        File dir = new File("orders");
        dir.mkdir();
        File writer = new File("orders/demo.dat");

        try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(writer))) {
            ostream.writeObject(order);
        } catch (IOException e) {
            LOGGER.error("Write object error: ", e);
        }
    }

    public void reader() {
        File reader = new File("orders/demo.dat");
        try (ObjectInputStream istream = new ObjectInputStream(new FileInputStream(reader))) {
            Order unknown = (Order) istream.readObject();
            System.out.println(unknown);
        } catch (Exception e) {
            LOGGER.error("Read object error: ", e);
        }
    }
}