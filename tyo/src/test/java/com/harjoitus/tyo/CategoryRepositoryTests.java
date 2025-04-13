package com.harjoitus.tyo;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.harjoitus.tyo.domain.Category;
import com.harjoitus.tyo.domain.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createNewCategory() {
        Category category = new Category("Science");
        categoryRepository.save(category);

        assertThat(category.getCategoryid()).isNotNull();
        assertThat(categoryRepository.findById(category.getCategoryid())).isPresent();
    }

    @Test
    public void findCategoryByName() {
        Category category = new Category("Fiction");
        categoryRepository.save(category);

        Optional<Category> categories = categoryRepository.findByName("Fiction");
        assertThat(categories).isNotEmpty();
        assertThat(categories.get().getName()).isEqualTo("Fiction");
    }
}
