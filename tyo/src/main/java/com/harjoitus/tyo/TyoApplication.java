package com.harjoitus.tyo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.harjoitus.tyo.domain.AppUser;
import com.harjoitus.tyo.domain.AppUserRepository;
import com.harjoitus.tyo.domain.Category;
import com.harjoitus.tyo.domain.CategoryRepository;
import com.harjoitus.tyo.domain.Product;
import com.harjoitus.tyo.domain.ProductRepository;

@SpringBootApplication
public class TyoApplication {

    private static final Logger log = LoggerFactory.getLogger(TyoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TyoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ProductRepository productRepository, CategoryRepository categoryRepository,AppUserRepository appUserRepository) {
        return (args) -> {
			//users
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			appUserRepository.save(user1);
			appUserRepository.save(user2);
			
			//categories
            Category category1 = new Category("Kitchen");
            categoryRepository.save(category1);
            Category category2 = new Category("Cooking");
            categoryRepository.save(category2);
            Category category3 = new Category("Decoration");
            categoryRepository.save(category3);
            Category category4 = new Category("Clothing");
            categoryRepository.save(category4);

            //products 
            productRepository.save(new Product("Plate", "1234-1234567", 5, category1));
            productRepository.save(new Product("Butterknife", "2345-1234567", 2, category1));
            productRepository.save(new Product("Fork", "3456-1234567", 2, category1));
            productRepository.save(new Product("Spoon", "4567-1234567", 2.5, category1));
            productRepository.save(new Product("Cutting Board", "1234-1234567", 20, category2));
            productRepository.save(new Product("Chef's Knife", "2234-2345678", 45, category2));
            productRepository.save(new Product("Frying Pan", "3234-3456789", 35, category2));
            productRepository.save(new Product("Denim Jacket", "2034-1122334", 60, category4));
            productRepository.save(new Product("Graphic T-Shirt", "2134-1222334", 25, category4));
            productRepository.save(new Product("Running Shoes", "2234-1322334", 80, category4));
            productRepository.save(new Product("Wool Scarf", "2334-1422334", 18, category4));
            productRepository.save(new Product("Leather Belt", "2434-1522334", 20, category4));
            productRepository.save(new Product("Table Lamp", "3034-2122334", 40, category3));
            productRepository.save(new Product("Wall Art Canvas", "3134-2222334", 35, category3));
            productRepository.save(new Product("Scented Candle", "3234-2322334", 12, category3));
            productRepository.save(new Product("Decorative Vase", "3334-2422334", 28, category3));
            productRepository.save(new Product("Throw Pillow Set", "3434-2522334", 30, category3));
            for (Product product : productRepository.findAll()) {
                log.info(product.toString());
            }
        };
    }
}
