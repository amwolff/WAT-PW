package com.mut.concurrentprogrammingcourse;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Store {
    final private List<ProductRack> productRacks;
    final private List<Semaphore> rackMtxs;
    final private Random randomSource;

    public Store(int n, int k, int m) {
        productRacks = new LinkedList<>();
        rackMtxs = new LinkedList<>();

        final Storekeeper storekeeper = new Storekeeper();
        for (int i = 0; i < n; i++) {
            final List<List<Product>> productGroups = new LinkedList<>();
            for (int j = 0; j < k; j++) {
                final List<Product> products = new LinkedList<>();
                for (int l = 0; l < m; l++) {
                    products.add(new Product(new ProductKind(j)));
                }
                productGroups.add(products);
            }

            productRacks.add(new ProductRack(productGroups, storekeeper, m));
            rackMtxs.add(new Semaphore(1, true));
        }

        randomSource = new Random();
    }

    public Product buy(ProductKind kind, boolean hurry) {
        if (hurry) {
            for (int i = 0; i < productRacks.size(); i++) {
                if (rackMtxs.get(i).tryAcquire()) {
                    final Product ret = productRacks.get(i).acquireOne(kind);
                    rackMtxs.get(i).release();
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

        final int idx = randomSource.nextInt(productRacks.size());

        Product ret = null;
        try {
            rackMtxs.get(idx).acquire();
            try {
                ret = productRacks.get(idx).acquireOne(kind);
            } finally {
                rackMtxs.get(idx).release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
