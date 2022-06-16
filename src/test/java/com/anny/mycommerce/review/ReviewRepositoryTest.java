package com.anny.mycommerce.review;

import com.anny.mycommerce.category.domain.Category;
import com.anny.mycommerce.category.domain.CategoryRepository;
import com.anny.mycommerce.common.domain.Money;
import com.anny.mycommerce.product.domain.Product;
import com.anny.mycommerce.product.domain.ProductRepository;
import com.anny.mycommerce.review.domain.Review;
import com.anny.mycommerce.review.domain.ReviewRepository;
import com.anny.mycommerce.store.domain.Store;
import com.anny.mycommerce.store.domain.StoreRepository;
import com.anny.mycommerce.user.domain.User;
import com.anny.mycommerce.user.domain.application.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Transactional
@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void 리뷰_저장() {
        Category category = categoryRepository.save(Category.of("가전제품"));
        User user = userRepository.save(User.forTest().build());
        Store store = storeRepository.save(Store.of()
                .name("test store")
                .adminId(user.getId())
                .build());
        Product product = productRepository.save(Product.of()
                .store(store)
                .category(category)
                .name("line mouse")
                .description("best mouse")
                .stock(100)
                .price(Money.of(25000L))
                .images(Arrays.asList())
                .build());

        Review review = Review.of()
                .content("유용한 마우스")
                .images(Arrays.asList())
                .productId(product.getId())
                .writerId(user.getId())
                .rate(3)
                .build();
        reviewRepository.save(review);
    }
}
