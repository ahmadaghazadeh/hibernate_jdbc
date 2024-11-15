package com.sevensky.hibernate_intro.domain;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Product extends BaseEntity {

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        if (!super.equals(o)) return false;

        return Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(getName());
        return result;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
