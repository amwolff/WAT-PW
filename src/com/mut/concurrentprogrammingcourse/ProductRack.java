package com.mut.concurrentprogrammingcourse;

import java.util.List;

public class ProductRack {
    final private List<List<Product>> productGroups;
    final private Storekeeper storekeeper;
    final private int shelfSize;

    public ProductRack(List<List<Product>> productGroups, Storekeeper storekeeper, int shelfSize) {
        this.productGroups = productGroups;
        this.storekeeper = storekeeper;
        this.shelfSize = shelfSize;
    }

    public Product acquireOne(ProductKind kind) {
        // fast path:
        for (List<Product> products : productGroups) {
            if (!products.isEmpty()) {
                final int idx = products.size() - 1;
                final Product aux = products.get(idx);
                if (aux.getKind().equals(kind)) {
                    products.remove(idx);
                    return aux;
                }
            }
        }

        // (continued) slow path:
        for (List<Product> products : productGroups) {
            if (products.isEmpty()) {
                for (int i = 0; i < shelfSize; i++) {
                    products.add(storekeeper.deliver(kind));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // add anyway
                    }
                }
                return products.remove(products.size() - 1);
            }
        }

        return null;
    }
}