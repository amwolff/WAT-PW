package com.mut.concurrentprogrammingcourse;

public class Main {

    public static void main(String[] args) {
        Store store = new Store(1, 1, 1);
        Thread c1 = new Thread(new Client(1, store, false, 1));
        Thread c2 = new Thread(new Client(2, store, false, 1));
        Thread c3 = new Thread(new Client(3, store, true, 1));
        c1.start();
        c2.start();
        c3.start();
        try {
            c1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            c3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
