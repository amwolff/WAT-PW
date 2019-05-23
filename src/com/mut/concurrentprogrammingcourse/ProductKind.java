package com.mut.concurrentprogrammingcourse;

public class ProductKind {
    final private int id;

    ProductKind(int id) {
        this.id = id;
    }

    Integer getId() {
        return id;
    }

    @Override
    final public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ProductKind productKind = (ProductKind) obj;
        return productKind.getId() == id;
    }
}
