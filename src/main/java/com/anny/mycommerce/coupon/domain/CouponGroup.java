package com.anny.mycommerce.coupon.domain;

import com.anny.mycommerce.common.domain.Money;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "coupon_groups")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long issuerId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int maxCount;

    @Column
    private int currentCount;

    @Embedded
    @AttributeOverride(name = "amount",column = @Column(name="sale_price"))
    private Money price;

    @Enumerated(EnumType.STRING)
    private CouponGroupStatus status;

    @Embedded
    private DiscountPolicy discountPolicy;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder(builderMethodName = "of")
    public CouponGroup(Long issuerId, String name, String description, int maxCount, Money price, DiscountPolicy discountPolicy) {
        Assert.notNull(issuerId,"쿠폰 발행자의 id는 필수입니다.");
        Assert.hasText(name,"쿠폰의 이름은 공백일수 없습니다.");
        Assert.state(maxCount>0,"쿠폰의 발행 개수는 0보다 커야합니다.");
        Assert.notNull(price,"쿠폰의 할인 가격은 필수입니다");
        Assert.notNull(discountPolicy,"쿠폰의 할인정책은 필수입니다.");

        this.issuerId = issuerId;
        this.name = name;
        this.description = description;
        this.maxCount = maxCount;
        this.currentCount = maxCount;
        this.price = price;
        this.status = CouponGroupStatus.ISSUED;
        this.discountPolicy = discountPolicy;
    }
}