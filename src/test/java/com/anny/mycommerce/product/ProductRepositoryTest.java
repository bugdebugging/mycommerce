package com.anny.mycommerce.product;

import com.anny.mycommerce.category.domain.Category;
import com.anny.mycommerce.category.domain.CategoryRepository;
import com.anny.mycommerce.common.domain.Image;
import com.anny.mycommerce.common.domain.Money;
import com.anny.mycommerce.product.domain.Product;
import com.anny.mycommerce.product.domain.ProductRepository;
import com.anny.mycommerce.store.domain.Store;
import com.anny.mycommerce.store.domain.StoreRepository;
import com.anny.mycommerce.user.domain.User;
import com.anny.mycommerce.user.domain.application.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void 상품_저장() {
        final Category category = Category.of("생활용품");
        categoryRepository.save(category);

        final User admin = User.forTest().build();
        userRepository.save(admin);

        final Store store = Store.of()
                .name("mycommerce")
                .adminId(admin.getId())
                .build();
        storeRepository.save(store);

        List<Image> images = Arrays.asList(Image.of("https://ilove/springboot/1")
                , Image.of("https://ilove/springboot/2")
                , Image.of("https://ilove/springboot/3"));

        Product product = Product.of()
                .store(store)
                .category(category)
                .images(images)
                .name("나이스 칫솔")
                .description("매우 좋은 칫솔입니다")
                .price(Money.of(3000L))
                .stock(100)
                .build();

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
    }
}
