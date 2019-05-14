package com.mut.concurrentprogrammingcourse;

public class ProductKind {
    private final Integer id;

    public ProductKind(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProductKind kind = (ProductKind) obj;
        return kind.getId().equals(getId());
    }
}
