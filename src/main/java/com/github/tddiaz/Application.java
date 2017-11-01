package com.github.tddiaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

//		DealService dealService = applicationContext.getBean("dealService", DealService.class);
//
//
//		List<ValidDeal> validDeals = new ArrayList<>();
//
//		for (int i = 0; i < 100000; i++) {
//
//			ValidDeal deal = new ValidDeal();
//			deal.setDealId(String.valueOf(i));
//			deal.setAmount(0.0);
//			deal.setDateTime(LocalDateTime.now());
//			deal.setFromCurrency(CurrencyCode.AED);
//			deal.setToCurrency(CurrencyCode.PHP);
//			deal.setFileName("test.csv");
//
//			validDeals.add(deal);
//		}
//
//		dealService.batchSave(validDeals, new ArrayList<>());
	}
}
