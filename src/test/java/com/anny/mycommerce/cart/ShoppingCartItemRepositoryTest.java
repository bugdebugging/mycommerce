package com.anny.mycommerce.cart;

import com.anny.mycommerce.cart.domain.ShoppingCartItem;
import com.anny.mycommerce.cart.domain.ShoppingCartItemRepository;
import com.anny.mycommerce.category.domain.Category;
import com.anny.mycommerce.category.domain.CategoryRepository;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
public class ShoppingCartItemRepositoryTest {
    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    StoreRepository storeRepository;

    @Test
    void 쇼핑카트아이템_저장() {
        User user = User.forTest().build();
        userRepository.save(user);

        Category category = Category.of("가전제품");
        categoryRepository.save(category);

        Store store = Store.of()
                .name("test store")
                .adminId(user.getId())
                .build();
        storeRepository.save(store);

        Product product = Product.forTest()
                .store(store)
                .category(category)
                .build();
        productRepository.save(product);

        ShoppingCartItem shoppingCartItem = ShoppingCartItem.of()
                .user(user)
                .product(product)
                .count(3)
                .build();

        ShoppingCartItem savedShoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
        assertNotNull(savedShoppingCartItem.getId());
    }
}
