package com.mut.concurrentprogrammingcourse;

public class ProductKind {
    private final int id;

    public ProductKind(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ProductKind productKind = (ProductKind) obj;
        return productKind.getId() == id;
    }
}
