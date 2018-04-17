package com.sample.instrument.pricedata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Vendor {

    private Long id;
    private String vendorName;
    private Set<TradeInstrumentPrice> prices;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "vendorId")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Set<TradeInstrumentPrice> getPrices() {
        return prices;
    }


    public void setPrices(Set<TradeInstrumentPrice> prices) {
        this.prices = prices;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return Objects.equals(vendorName, vendor.vendorName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vendorName);
    }
}
