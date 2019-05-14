package com.mut.concurrentprogrammingcourse;

public class Main {

    public static void main(String[] args) {
        Store store = new Store(1, 1, 1);
        Thread c1 = new Thread(new Client(1, store, false, 1));
        c1.start();
        try {
            c1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
