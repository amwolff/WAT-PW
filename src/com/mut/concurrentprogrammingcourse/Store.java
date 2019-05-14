package com.mut.concurrentprogrammingcourse;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Store {
    final private List<ProductShelf> productShelves;
    final private List<Semaphore> shelfMtxs;
    final private Random randomSource;

    public Store(int n, int k, int m) {
        final List<List<Product>> productGroups = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            final List<Product> products = new LinkedList<>();
            for (int j = 0; j < m; j++) {
                products.add(new Product(new ProductKind(j)));
            }
            productGroups.add(products);
        }

        productShelves = new LinkedList<>();
        shelfMtxs = new LinkedList<>();
        final Storekeeper storekeeper = new Storekeeper();
        for (int i = 0; i < n; i++) {
            productShelves.add(new ProductShelf(productGroups, storekeeper, m));
            shelfMtxs.add(new Semaphore(1, true));
        }
        randomSource = new Random();
    }

    public Product buy(ProductKind kind, boolean hurry) {
        if (hurry) {
            for (int i = 0; i < productShelves.size(); i++) {
                final Semaphore mtx = shelfMtxs.get(i);
                if (mtx.tryAcquire()) {
                    final Product ret = productShelves.get(i).acquireOne(kind);
                    mtx.release();
                    return ret;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        final int idx = randomSource.nextInt(productShelves.size());
        final Semaphore mtx = shelfMtxs.get(idx);

        Product ret = null;
        try {
            mtx.acquire();
            try {
                ret = productShelves.get(idx).acquireOne(kind);
            } finally {
                mtx.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
