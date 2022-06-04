package com.anny.mycommerce.category;

import com.anny.mycommerce.category.domain.Category;
import com.anny.mycommerce.category.domain.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void 카테고리_저장() {
        Category category = Category.of("가전용품");
        Category savedCategory = categoryRepository.save(category);
        assertNotNull(savedCategory.getId());
    }
}
