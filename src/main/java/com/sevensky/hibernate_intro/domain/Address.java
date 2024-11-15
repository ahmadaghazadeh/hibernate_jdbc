package com.sevensky.hibernate_intro.domain;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {
    public Address(String address, String city, String state, String zipCode) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    private String address;
    private String city;
    private String state;
    private String zipCode;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address1)) return false;

        return Objects.equals(getAddress(), address1.getAddress()) && Objects.equals(getCity(),
                address1.getCity()) && Objects.equals(getState(), address1.getState())
                && Objects.equals(getZipCode(), address1.getZipCode());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getAddress());
        result = 31 * result + Objects.hashCode(getCity());
        result = 31 * result + Objects.hashCode(getState());
        result = 31 * result + Objects.hashCode(getZipCode());
        return result;
    }
}
