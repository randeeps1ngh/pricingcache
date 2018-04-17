package com.sample.instrument.pricedata;


import com.sample.instrument.pricedata.data.PriceCacheDataRepository;
import com.sample.instrument.pricedata.data.VendorRepository;
import com.sample.instrument.pricedata.domain.TradeInstrumentPrice;
import com.sample.instrument.pricedata.domain.Vendor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase

public class PriceCacheDataRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PriceCacheDataRepository priceCacheDataRepository;

    @Autowired
    private VendorRepository vendorRepository;


    @Test
    public void vendorRepository() {


        Vendor bloomberg = new Vendor();
        bloomberg.setVendorName("Bloomberg");

        vendorRepository.save(bloomberg);

        Vendor testbloomberg = vendorRepository.findOne(bloomberg.getId());

        assertThat(testbloomberg.getVendorName().equals(bloomberg.getVendorName()));

    }

    @Test
    public void priceDataRepository() {

        Vendor dealogic = new Vendor();
        dealogic.setVendorName("DealLogic");

        vendorRepository.save(dealogic);

        TradeInstrumentPrice tradeInstrumentPrice4 = new TradeInstrumentPrice();
        tradeInstrumentPrice4.setVendor(dealogic);
        tradeInstrumentPrice4.setTradeInstrument("GBPUSD");
        tradeInstrumentPrice4.setPrice(1.4);

        priceCacheDataRepository.save(tradeInstrumentPrice4);

        TradeInstrumentPrice tradeInstrumentPrice5 = new TradeInstrumentPrice();
        tradeInstrumentPrice5.setVendor(dealogic);
        tradeInstrumentPrice5.setTradeInstrument("GBPUSD");
        tradeInstrumentPrice5.setPrice(1.5);

        priceCacheDataRepository.save(tradeInstrumentPrice5);


        TradeInstrumentPrice tradeInstrumentPrice6 = new TradeInstrumentPrice();
        tradeInstrumentPrice6.setVendor(dealogic);
        tradeInstrumentPrice6.setTradeInstrument("GBPUSD");
        tradeInstrumentPrice6.setPrice(1.6);

        priceCacheDataRepository.save(tradeInstrumentPrice6);

        List<TradeInstrumentPrice> tradeInstrumentPriceList = priceCacheDataRepository.findByVendorId(dealogic.getId());

        assertThat(tradeInstrumentPriceList.get(0).getVendor().equals(dealogic.getVendorName()));
        assertEquals(3,tradeInstrumentPriceList.size());
        assertEquals("DealLogic",tradeInstrumentPriceList.get(0).getVendor().getVendorName());
    }
}
