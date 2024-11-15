package com.works.useStream;

import java.util.ArrayList;
import java.util.List;

public class Result {

    public List<Customer> dataResult(int size) {
        List<Customer> ls = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            var customer = new Customer(i, "name-"+i, "surname" +i);
            ls.add(customer);
        }
        return ls;
    }


}
