package com.mut.concurrentprogrammingcourse;

public class Storekeeper {
    Product deliver(ProductKind kind) {
        return new Product(kind);
    }
}
