package com.mut.concurrentprogrammingcourse;

import java.util.List;

public class ProductShelf {
    final private List<List<Product>> productGroups;
    final private Storekeeper storekeeper;
    final private int productGroupPopulation;

    public ProductShelf(List<List<Product>> productGroups, Storekeeper storekeeper, int productGroupPopulation) {
        this.productGroups = productGroups;
        this.storekeeper = storekeeper;
        this.productGroupPopulation = productGroupPopulation;
    }

    public Product buy(ProductKind kind) {
        // fast path:
        for (List<Product> products : productGroups) {
            if (!products.isEmpty()) {
                int idx = products.size() - 1;
                Product aux = products.get(idx);
                if (aux.getKind().equals(kind)) {
                    products.remove(idx);
                    return aux;
                }
            }
        }
        // (continued) slow path:
        Product ret = null;
        for (List<Product> products : productGroups) {
            if (products.isEmpty()) {
                for (int i = 0; i < productGroupPopulation; i++) {
                    products.add(storekeeper.deliver(kind));
                    if (i == productGroupPopulation - 1) {
                        ret = products.remove(products.size() - 1);
                    }
                }
            }
        }
        return ret;
    }
}
