package com.micb2b.purchasing;

import com.micb2b.purchasing.service.ProdDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
@Slf4j
public class ShoppeApplication {

	public static void main(String[] args) throws IOException, ParseException {
		long lStart = System.currentTimeMillis();
		ConfigurableApplicationContext ctx = SpringApplication.run(ShoppeApplication.class, args);

		log.info("****************************** Shoppe batch job started  ******************************");
		ProdDataService service = ctx.getBean(ProdDataService.class);

		log.info("****************************** save prods ******************************");
		service.saveProsds();

//		log.info("****************************** calculate orders ******************************");
//		service.calculateOrders();
		log.info("****************************** Shoppe batch job finished  ******************************");
	}
}
