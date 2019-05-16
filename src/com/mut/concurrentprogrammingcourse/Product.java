package com.mut.concurrentprogrammingcourse;

class Product {
    final private ProductKind kind;

    Product(ProductKind kind) {
        this.kind = kind;
    }

    ProductKind getKind() {
        return kind;
    }
}
