package com.mut.concurrentprogrammingcourse;

class Storekeeper {
    Product deliver(ProductKind kind) {
        return new Product(kind);
    }
}
