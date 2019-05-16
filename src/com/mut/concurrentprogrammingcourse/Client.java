package com.mut.concurrentprogrammingcourse;

import java.util.Random;

final public class Client implements Runnable {
    final private int id;
    final private Store store;
    final private int maxProductKinds;
    final private boolean inHurry;
    final private Random randSrc;

    Client(int id, Store store, int maxProductKinds, boolean inHurry, Random randSrc) {
        this.id = id;
        this.store = store;
        this.maxProductKinds = maxProductKinds;
        this.inHurry = inHurry;
        this.randSrc = randSrc;
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
        final Product bought = store.buy(new ProductKind(randSrc.nextInt(maxProductKinds)), inHurry);
        if (bought == null) {
            System.out.printf("Client no. %d left the store\n", id);
            return;
        }
        System.out.printf("Client no. %d bought product of kind no. %d\n", id, bought.getKind().getId());
    }
}
