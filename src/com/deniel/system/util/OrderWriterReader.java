package com.deniel.system.util;

import com.deniel.system.domain.Order;
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

    public void write(Order order) {
        String fileName = "orders/demo.dat";

        File dir = new File("orders");
        dir.mkdir();
        File writer = new File(fileName);
        if (!writer.exists()) {
            try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(writer))) {
                ArrayList<Order> collection = new ArrayList<>();
                collection.add(order);
                sort(collection);
                ostream.writeObject(collection);
            } catch (IOException e) {
                System.err.println(e);
            }
        } else {
            try (ObjectInputStream istream = new ObjectInputStream(new FileInputStream(fileName))) {
                ArrayList<Order> collection = (ArrayList) istream.readObject();
                collection.add(order);
                sort(collection);
                try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(writer))) {
                    ostream.writeObject(collection);
                } catch (IOException e) {
                    System.err.println(e);
                }
            } catch (Exception e) {
                System.err.println(e);
                System.err.println("IO Error");
            }
        }
    }

    public void reader() {
        File reader = new File("orders/demo.dat");
        try (ObjectInputStream istream = new ObjectInputStream(new FileInputStream(reader))) {
            ArrayList<Order> collection = (ArrayList) istream.readObject();
            String answer = whatToShow();
            if (answer.equals("ALL")) {
                System.out.println(collection);
            }
            for (Order pair : collection) {
                if (pair.getId().equals(answer)) {
                    System.out.println(pair);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("IO Error");
        }
    }

    public ArrayList<Order> sort(ArrayList<Order> list) {
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

    public String whatToShow() throws IOException {
        System.out.println("Enter your order number or type ALL to see all of orders");
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
        String answer = keyReader.readLine();
        return answer;
    }
}