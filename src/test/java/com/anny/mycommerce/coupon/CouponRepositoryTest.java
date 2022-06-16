package com.anny.mycommerce.coupon;

import com.anny.mycommerce.common.domain.Money;
import com.anny.mycommerce.coupon.domain.Coupon;
import com.anny.mycommerce.coupon.domain.CouponRepository;
import com.anny.mycommerce.coupon.domain.DiscountPolicy;
import com.anny.mycommerce.coupon.domain.ValidPeriod;
import com.anny.mycommerce.user.domain.User;
import com.anny.mycommerce.user.domain.application.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
public class CouponRepositoryTest {
    @Autowired
    CouponRepository couponRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void 쿠폰_저장() {
        final User user = User.forTest().build();
        User savedUser = userRepository.save(user);

        final DiscountPolicy discountPolicy = DiscountPolicy.of()
                .limitPrice(Money.ZERO)
                .period(ValidPeriod.of()
                        .validFrom(LocalDateTime.now().plusHours(2))
                        .validTo(LocalDateTime.now().plusDays(1))
                        .build())
                .build();

        Coupon coupon = Coupon.of()
                .discountMoney(Money.of(10000L))
                .discountPolicy(discountPolicy)
                .userId(savedUser.getId())
                .build();

        Coupon savedCoupon = couponRepository.save(coupon);
        assertNotNull(savedCoupon.getId());
    }
}
