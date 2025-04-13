package com.harjoitus.tyo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.harjoitus.tyo.domain.Category;
import com.harjoitus.tyo.domain.CategoryRepository;
import com.harjoitus.tyo.domain.Product;
import com.harjoitus.tyo.domain.ProductRepository;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void saveProductShouldSaveCorrectly() {
        Category category = new Category("Books");
        categoryRepository.save(category);

        Product product = new Product("Test Book", "123456", 19.99, category);
        productRepository.save(product);

        assertThat(product.getId()).isNotNull();
    }

    @Test
    public void findByNameShouldReturnCorrectProduct() {
        Category category = new Category("Movies");
        categoryRepository.save(category);

        Product product = new Product("DVD Movie", "MOV789", 9.99, category);
        productRepository.save(product);

        List<Product> result = productRepository.findByName("DVD Movie");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getIsbn()).isEqualTo("MOV789");
    }
}

