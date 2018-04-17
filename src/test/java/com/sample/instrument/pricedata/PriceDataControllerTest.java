package com.sample.instrument.pricedata;


import com.sample.instrument.pricedata.controller.PriceDataCacheController;
import com.sample.instrument.pricedata.data.PriceCacheDataRepository;
import com.sample.instrument.pricedata.data.VendorRepository;
import com.sample.instrument.pricedata.domain.TradeInstrumentPrice;
import com.sample.instrument.pricedata.domain.Vendor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.sample.instrument.pricedata.controller.Paths.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PriceDataCacheController.class)
public class PriceDataControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PriceDataCacheController priceDataCacheController;

    @MockBean
    private PriceCacheDataRepository priceCacheDataRepository;

    @MockBean
    private VendorRepository vendorRepository;


    @Test
    public void getPricesForAVendor() throws Exception {


        TradeInstrumentPrice tradeInstrumentPrice = new TradeInstrumentPrice();
        tradeInstrumentPrice.setTradeInstrument("EURUSD");
        tradeInstrumentPrice.setPrice(1.1);

        Vendor bloomberg = new Vendor();
        bloomberg.setVendorName("bloomberg");
        tradeInstrumentPrice.setVendor(bloomberg);

        List<TradeInstrumentPrice> tradeInstrumentPriceList = singletonList(tradeInstrumentPrice);

        given(priceDataCacheController.getAllPricesByVendor(bloomberg.getVendorName())).willReturn(tradeInstrumentPriceList);

        mvc.perform(get(VERSION + GET_PRICES_BY_VENDOR + bloomberg.getVendorName())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].tradeInstrument", is(tradeInstrumentPrice.getTradeInstrument())));

    }


    @Test
    public void getPricesForAnInstrument() throws Exception {


        TradeInstrumentPrice tradeInstrumentPrice = new TradeInstrumentPrice();
        tradeInstrumentPrice.setTradeInstrument("EURUSD");
        tradeInstrumentPrice.setPrice(1.1);

        Vendor bloomberg = new Vendor();
        bloomberg.setVendorName("bloomberg");
        tradeInstrumentPrice.setVendor(bloomberg);

        List<TradeInstrumentPrice> tradeInstrumentPriceList = singletonList(tradeInstrumentPrice);

        given(priceDataCacheController.getAllPricesByInstrument(tradeInstrumentPrice.getTradeInstrument())).willReturn(tradeInstrumentPriceList);

        mvc.perform(get(VERSION + GET_PRICES_BY_INSTRUMENT + tradeInstrumentPrice.getTradeInstrument())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tradeInstrument", is(tradeInstrumentPrice.getTradeInstrument())));

    }
}
