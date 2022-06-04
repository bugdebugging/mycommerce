package com.anny.mycommerce.user;

import com.anny.mycommerce.common.domain.Address;
import com.anny.mycommerce.user.domain.User;
import com.anny.mycommerce.user.domain.application.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void 사용자_저장() {
        final String email = "yousung@gmail.com";
        final String password = "tmp_password";
        final String phone = "010-0000-0000";
        final Address address = Address.of("서울 노원구 월계동 222-22", "4층", "35555");

        User user = User.of()
                .email(email)
                .password(password)
                .phone(phone)
                .address(address)
                .build();
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
    }
}
