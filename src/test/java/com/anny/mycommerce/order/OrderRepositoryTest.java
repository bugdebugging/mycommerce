package com.anny.mycommerce.order;

import com.anny.mycommerce.category.domain.Category;
import com.anny.mycommerce.category.domain.CategoryRepository;
import com.anny.mycommerce.common.domain.Address;
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
public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    StoreRepository storeRepository;

    @Test
    void 주문_저장() {
        final User user = User.forTest().build();
        final Category category = Category.of("가전제품");
        final Store store = Store.of()
                .name("test store")
                .admin(user)
                .build();

        final Product product1 = Product.of()
                .price(Money.of(1000000L))
                .stock(100)
                .description("notebook~")
                .name("notebook")
                .images(Arrays.asList())
                .category(category)
                .store(store)
                .build();
        final Product product2 = Product.of()
                .price(Money.of(30000L))
                .stock(2500)
                .description("mouse~")
                .name("mouse")
                .images(Arrays.asList())
                .category(category)
                .store(store)
                .build();

        userRepository.save(user);
        categoryRepository.save(category);
        storeRepository.save(store);
        productRepository.save(product1);
        productRepository.save(product2);

        final List<OrderItem> orderItems = Arrays.asList(OrderItem.of().productId(1L).count(3).price(product1.getPrice()).build(),
                OrderItem.of().productId(2L).count(2).price(product2.getPrice()).build());

        Order order = Order.of()
                .orderer(user)
                .paymentType(PaymentType.CARD)
                .shippingAddress(Address.of("서울시 분당 서현 ~~", "3층", "11111"))
                .orderItems(orderItems)
                .build();

        Order savedOrder = orderRepository.save(order);
        assertNotNull(savedOrder.getId());
    }
}
