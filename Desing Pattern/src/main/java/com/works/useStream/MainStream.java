package com.works.useStream;

import java.util.*;

public class MainStream {
    public static void main(String[] args) {

        User u1 = new User();
        u1.setId(1);
        u1.setName("Ali");
        u1.setSurname("Bilmem");

        User u2 = new User();
        u2.setId(1);
        u2.setName("Ali");
        u2.setSurname("Bilmem");

        System.out.println(u1.hashCode());
        System.out.println(u2.hashCode());

        Set<User> users = new LinkedHashSet<>();
        users.add(u1);
        users.add(u2);
        //System.out.println(users);

        Result result = new Result();
        List<Customer> ls = result.dataResult(10);

        long start = System.currentTimeMillis();
        ls.parallelStream()
                .filter(item -> item.name().contains("a") || item.cid() > 10)
                .forEach(item -> {
                    try {
                        Thread.sleep(100);
                        System.out.println(item);
                    }catch (Exception ex) {}
                });
        long end = System.currentTimeMillis();
        long between = end - start;
        System.out.println("stream : " + between);

        Object objName = "Erkan";
        if (objName instanceof String) {
            String st = (String) objName;
        }

        if (objName instanceof String st) {
            System.out.println(st);
        }

        switch (objName) {
            case String item -> System.out.println(item);
            case Boolean item -> System.out.println(item);
            default -> throw new IllegalStateException("Unexpected value: " + objName);
        }

        List<String> lsx = null;
        lsx = new LinkedList<>();
        lsx = new Vector<>();
        switch (lsx) {
            case Set x -> System.out.println(x.size());
            default -> throw new IllegalStateException("Unexpected value: " + objName);
        }



    }
}


// stream : 102315
// paralel : 12887