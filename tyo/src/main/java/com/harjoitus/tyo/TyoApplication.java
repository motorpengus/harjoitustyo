package com.harjoitus.tyo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.harjoitus.tyo.domain.Product;
import com.harjoitus.tyo.domain.ProductRepository;

@SpringBootApplication
public class TyoApplication {
	private static final Logger log = LoggerFactory.getLogger(TyoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TyoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ProductRepository productRepository){
		return (args) -> {
			productRepository.save(new Product("Lautanen", "1234-1234567", 5));
			productRepository.save(new Product("Voiveitsi","2345-1234567",2));
			productRepository.save(new Product("Haarukka","3456-1234567",2));
			productRepository.save(new Product("Lusikka","4567-1234567",2.5));

			for (Product product : productRepository.findAll())
				log.info(product.toString());
		};
	}
}
