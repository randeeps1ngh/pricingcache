package com.sample.instrument.pricedata.data;

import com.sample.instrument.pricedata.domain.TradeInstrumentPrice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PriceCacheDataRepository extends CrudRepository<TradeInstrumentPrice,Long> {

    List<TradeInstrumentPrice> findByVendorId(Long vendorId);


    List<TradeInstrumentPrice> findByTradeInstrument(String tradeInstrument);



}
