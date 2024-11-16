package com.sevensky.hibernate_intro.domain;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class OrderApproval extends BaseEntity {

    private String approveBy;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderApproval that)) return false;
        if (!super.equals(o)) return false;

        return Objects.equals(getApproveBy(), that.getApproveBy());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(getApproveBy());
        return result;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }
}
