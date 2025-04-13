package com.harjoitus.tyo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.harjoitus.tyo.web.CategoryController;
import com.harjoitus.tyo.web.ProductController;

@SpringBootTest
class TyoApplicationTests {

	@Autowired
	private ProductController productController;
	@Autowired
	private CategoryController categoryController;

	@Test
	public void contextLoads() {
		assertThat(productController).isNotNull();
		assertThat(categoryController).isNotNull();
		
	}

}
