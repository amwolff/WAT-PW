package com.mut.concurrentprogrammingcourse;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final int n = 2;
        final int k = 4;
        final int m = 6;
        final int maxClients = 1500;
        final Random randomSource = new Random();

        final Store store = new Store(n, k, m);

        List<Thread> clientThreads = new LinkedList<>();
        for (int i = 0; i < maxClients; i++) {
            clientThreads.add(new Thread(new Client(i, store, k, randomSource.nextBoolean())));
        }

        clientThreads.forEach(clientThread -> {
            clientThread.start();
            try {
                Thread.sleep(randomSource.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        clientThreads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
