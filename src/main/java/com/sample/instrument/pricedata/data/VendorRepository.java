package com.sample.instrument.pricedata.data;

import com.sample.instrument.pricedata.domain.Vendor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VendorRepository extends CrudRepository<Vendor,Long> {

    Optional<Vendor> findByVendorName(String vendorName);
}
