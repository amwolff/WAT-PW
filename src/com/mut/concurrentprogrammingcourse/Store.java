package com.mut.concurrentprogrammingcourse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

class Store {
    final private List<ProductRack> productRacks;
    final private List<Semaphore> rackMtxs;
    final private Random randSrc;

    Store(int n, int k, int m, Random randSrc) {
        productRacks = new ArrayList<>();
        rackMtxs = new ArrayList<>();

        final Storekeeper storekeeper = new Storekeeper();
        for (int i = 0; i < n; i++) {
            final List<List<Product>> productGroups = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                final List<Product> products = new LinkedList<>();
                for (int l = 0; l < m; l++) {
                    products.add(new Product(new ProductKind(j)));
                }
                productGroups.add(products);
            }

            productRacks.add(new ProductRack(productGroups, m, storekeeper));
            rackMtxs.add(new Semaphore(1, true));
        }

        this.randSrc = randSrc;
    }

    Product buy(ProductKind kind, boolean hurry) {
        if (hurry) {
            for (int i = 0; i < productRacks.size(); i++) {
                if (rackMtxs.get(i).tryAcquire()) {
                    final Product ret = productRacks.get(i).acquireOne(kind);
                    rackMtxs.get(i).release();
                    return ret;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        final int idx = randSrc.nextInt(productRacks.size());

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
