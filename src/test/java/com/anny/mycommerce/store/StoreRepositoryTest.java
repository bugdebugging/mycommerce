package com.anny.mycommerce.store;

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
public class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void 스토어_저장() {
        final User user=User.forTest().build();
        final String name = "let's car~";
        userRepository.save(user);

        Store store = Store.of()
                .name(name)
                .adminId(user.getId())
                .build();
        Store savedStore = storeRepository.save(store);
        assertNotNull(savedStore.getId());
    }
}
