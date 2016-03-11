package com.deniel.system.util;

import com.deniel.system.domain.Order;

import java.io.*;

/**
 * Created by Deniel on 11.03.2016.
 */
public class OrderWriterReader {

    public void write(Order order) throws IOException {
        File fw = new File("C://demo.dat");
        try {
            ObjectOutputStream ostream =
                    new ObjectOutputStream(
                            new FileOutputStream(fw));
            ostream.writeObject(order);
            ostream.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void reader() throws IOException {
        File fr = new File("C://demo.dat");
        try {
            ObjectInputStream istream =
                    new ObjectInputStream(
                            new FileInputStream(fr));
            Order unknown =
                    (Order) istream.readObject();
            istream.close();
            System.out.println(unknown);
        } catch (ClassNotFoundException ce) {
            System.err.println(ce);
            System.err.println("Класс не существует");
        } catch (FileNotFoundException fe) {
            System.err.println(fe);
            System.err.println("Файл не найден");
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.err.println("Ошибка доступа");
        }
    }
}