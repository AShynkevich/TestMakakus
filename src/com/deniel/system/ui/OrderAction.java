package com.deniel.system.ui;

import com.deniel.system.domain.Order;
import com.deniel.system.util.OrderWriterReader;
import com.deniel.system.util.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Created by Deniel on 03.03.2016.
 */
public class OrderAction {

    public static final String ONLY_NUMBER_PATTERN = "\\d+";
    public static final String FLOAT_PATTERN = "\\d+(\\.\\d*)?";
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Order order = new Order();
    OrderWriterReader orderWriterReader = new OrderWriterReader();

    public void inputOrder() throws IOException {

        System.out.println("Input Id: ");
        order.setId(reader.readLine());

        System.out.println("Input order name: ");
        order.setOrderName(reader.readLine());

        String value = Validation.getValidString("Input amount [example: 50; 2]: ", ONLY_NUMBER_PATTERN);
        order.setAmount(Integer.parseInt(value));

        value = Validation.getValidString("Input price [example: 30; 20.5]: ", FLOAT_PATTERN);
        order.setPrice(new BigDecimal(value));

        orderWriterReader.write(order);
    }

    public void outputOrder() throws IOException, ClassNotFoundException {
        System.out.println("Your order is:");
        orderWriterReader.reader();

    }


}
