package com.sample.instrument.pricedata.controller;


import com.sample.instrument.pricedata.data.PriceCacheDataRepository;
import com.sample.instrument.pricedata.data.VendorRepository;
import com.sample.instrument.pricedata.domain.TradeInstrumentPrice;
import com.sample.instrument.pricedata.domain.Vendor;
import com.sample.instrument.pricedata.exception.PriceDataCacheException;
import com.sample.instrument.pricedata.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sample.instrument.pricedata.controller.Paths.GET_PRICES_BY_INSTRUMENT;
import static com.sample.instrument.pricedata.controller.Paths.GET_PRICES_BY_VENDOR;
import static com.sample.instrument.pricedata.controller.Paths.VERSION;


@RestController
@RequestMapping(value = VERSION)
public class PriceDataCacheController {

    protected final Logger logger = LoggerFactory.getLogger(PriceDataCacheController.class);

    @Autowired
    private PriceCacheDataRepository priceCacheDataRepository;

    @Autowired
    private VendorRepository vendorRepository;


    /**
     * Get All prices for given Vendor
     */
    @Cacheable("pricesForVendorCache")
    @GetMapping(GET_PRICES_BY_VENDOR + "{vendorName}")
    public List<TradeInstrumentPrice> getAllPricesByVendor(@PathVariable(value = "vendorName") String vendorName) throws Exception {

        logger.info("PriceDataCacheServiceImpl : getAllPricesByVendor called for " + vendorName);
        Vendor vendor = vendorRepository.findByVendorName(vendorName).orElseThrow(() ->
                new ResourceNotFoundException("Vendor Repository", "vendorName", vendorName));

        return priceCacheDataRepository.findByVendorId(vendor.getId());

    }

    /**
     * Get Prices for given Trade Instrument
     *
     * @param tradeInstrument
     * @return
     * @throws PriceDataCacheException
     */

    @GetMapping(GET_PRICES_BY_INSTRUMENT + "{tradeInstrument}")
    @Cacheable("pricesForInstrumentCache")
    public List<TradeInstrumentPrice> getAllPricesByInstrument(@PathVariable(value = "tradeInstrument") String tradeInstrument) throws Exception {

        logger.info("PriceDataCacheServiceImpl : getAllPricesByInstrument called for " + tradeInstrument);
        return priceCacheDataRepository.findByTradeInstrument(tradeInstrument);
    }
}
