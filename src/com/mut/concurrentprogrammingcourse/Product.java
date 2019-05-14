package com.mut.concurrentprogrammingcourse;

public class Product {
    final private ProductKind kind;

    public Product(ProductKind kind) {
        this.kind = kind;
    }

    public ProductKind getKind() {
        return kind;
    }
}
