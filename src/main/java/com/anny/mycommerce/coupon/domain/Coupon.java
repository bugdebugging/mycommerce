package com.anny.mycommerce.coupon.domain;

import com.anny.mycommerce.common.domain.Money;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "coupons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Enumerated
    private CouponStatus status;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "sale_price"))
    private Money discountMoney;

    @Embedded
    private DiscountPolicy discountPolicy;

    @Column
    private LocalDateTime usedAt;

    @Builder(builderMethodName = "of")
    public Coupon(Long userId, Money discountMoney, DiscountPolicy discountPolicy) {
        Assert.notNull(userId, "쿠폰의 소유자의 id는 필수입니다.");
        Assert.notNull(discountMoney, "할인되는 금액은 필수입니다.");
        Assert.notNull(discountPolicy, "쿠폰 적용 조건은 필수입니다.");

        this.userId = userId;
        this.status = CouponStatus.AVAILABLE;
        this.discountMoney = discountMoney;
        this.discountPolicy = discountPolicy;
    }
}
