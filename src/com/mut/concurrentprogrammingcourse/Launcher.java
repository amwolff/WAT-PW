package com.mut.concurrentprogrammingcourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

final class Launcher {
    final private int n;
    final private int k;
    final private int m;
    final private Store store;
    final private int maxClients;
    final private Random randSrc;

    Launcher(int n, int k, int m, int maxClients) {
        this.n = n;
        this.k = k;
        this.m = m;
        this.maxClients = maxClients;

        randSrc = new Random();
        store = new Store(this.n, this.k, this.m, randSrc);
    }

    void run() {
        final List<Thread> clientThreads = new ArrayList<>();
        for (int i = 0; i < maxClients; i++) {
            clientThreads.add(new Thread(new Client(i, store, k, randSrc.nextBoolean(), randSrc)));
        }

        clientThreads.forEach(thread -> {
            thread.start();
            try {
                Thread.sleep(randSrc.nextInt(250));
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
