package com.mut.concurrentprogrammingcourse;

import java.util.Random;

public class Client implements Runnable {
    final private int id;
    final private Store store;
    final private boolean inHurry;
    final private Random randomSource;
    final private int maxProductKinds;

    public Client(int id, Store store, boolean inHurry, int maxProductKinds) {
        this.id = id;
        this.store = store;
        this.inHurry = inHurry;
        randomSource = new Random();
        this.maxProductKinds = maxProductKinds;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Product bought = store.buy(new ProductKind(randomSource.nextInt(maxProductKinds)), inHurry);
        if (bought == null) {
            System.out.printf("Client %d left the store\n", id);
            return;
        }
        System.out.printf("Client %d bought product of kind %d\n", id, bought.getKind().getId());
    }
}
