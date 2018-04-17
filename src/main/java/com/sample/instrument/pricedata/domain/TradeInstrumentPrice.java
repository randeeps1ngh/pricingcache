package com.sample.instrument.pricedata.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class TradeInstrumentPrice {

	private Long id;
	private String tradeInstrument;
	private Double price;
	private Vendor vendor;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}


	@ManyToOne
	@JoinColumn(name = "vendorId")
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradeInstrument() {
		return tradeInstrument;
	}

	public void setTradeInstrument(String tradeInstrument) {
		this.tradeInstrument = tradeInstrument;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TradeInstrumentPrice that = (TradeInstrumentPrice) o;
		return Objects.equals(tradeInstrument, that.tradeInstrument) &&
				Objects.equals(price, that.price) &&
				Objects.equals(vendor, that.vendor);
	}

	@Override
	public int hashCode() {

		return Objects.hash(tradeInstrument, price, vendor);
	}
}
