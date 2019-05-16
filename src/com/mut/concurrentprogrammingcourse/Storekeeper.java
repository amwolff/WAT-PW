package com.mut.concurrentprogrammingcourse;

public class Storekeeper {
    public Product deliver(ProductKind kind) {
        return new Product(kind);
    }
}
