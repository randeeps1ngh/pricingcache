package com.sample.instrument.pricedata;

import com.sample.instrument.pricedata.data.PriceCacheDataRepository;
import com.sample.instrument.pricedata.data.VendorRepository;
import com.sample.instrument.pricedata.domain.TradeInstrumentPrice;
import com.sample.instrument.pricedata.domain.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableCaching
public class PricedataApplication implements CommandLineRunner {

	@Autowired
	private PriceCacheDataRepository priceCacheDataRepository;

	@Autowired
	private VendorRepository vendorRepository;


	public static void main(String[] args) {
		SpringApplication.run(PricedataApplication.class, args);
	}


	@Override
	@Transactional
	public void run(String... strings) throws Exception {

		Vendor bloomberg= new Vendor();
		bloomberg.setVendorName("Bloomberg");

		vendorRepository.save(bloomberg);

		Vendor dealogic = new Vendor();
		dealogic.setVendorName("Dealogic");

		vendorRepository.save(dealogic);


		TradeInstrumentPrice tradeInstrumentPrice1 = new TradeInstrumentPrice();
		tradeInstrumentPrice1.setVendor(bloomberg);
		tradeInstrumentPrice1.setTradeInstrument("EURUSD");
		tradeInstrumentPrice1.setPrice(1.1);

		priceCacheDataRepository.save(tradeInstrumentPrice1);

		TradeInstrumentPrice tradeInstrumentPrice2 = new TradeInstrumentPrice();
		tradeInstrumentPrice2.setVendor(bloomberg);
		tradeInstrumentPrice2.setTradeInstrument("EURUSD");
		tradeInstrumentPrice2.setPrice(1.2);

		priceCacheDataRepository.save(tradeInstrumentPrice2);

		TradeInstrumentPrice tradeInstrumentPrice3 = new TradeInstrumentPrice();
		tradeInstrumentPrice3.setVendor(bloomberg);
		tradeInstrumentPrice3.setTradeInstrument("EURUSD");
		tradeInstrumentPrice3.setPrice(1.3);

		priceCacheDataRepository.save(tradeInstrumentPrice3);

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


		priceCacheDataRepository.findByVendorId(bloomberg.getId()).stream().forEach(tradeInstrumentPrice ->
		System.out.println(tradeInstrumentPrice.getTradeInstrument()));


	}



}
