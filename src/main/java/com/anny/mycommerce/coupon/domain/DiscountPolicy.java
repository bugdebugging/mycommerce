package com.anny.mycommerce.coupon.domain;

import com.anny.mycommerce.common.domain.Money;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDateTime;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiscountPolicy {
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "limit_price"))
    private Money limitPrice;

    @Column
    private LocalDateTime validFrom;

    @Column
    private LocalDateTime validTo;

    @Builder
    public DiscountPolicy(Money limitPrice, LocalDateTime validFrom, LocalDateTime validTo) {
        Assert.state(validFrom == null && validTo != null, "유효기간은 시작시간과 종료시간이 모두 필요합니다.");
        Assert.state(validFrom != null && validTo == null, "유효기간은 시작시간과 종료시간이 모두 필요합니다.");

        this.limitPrice = limitPrice;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public boolean canApplyCouponOn(Money money, LocalDateTime timestamp) {
        if (Money.max(limitPrice, money).equals(limitPrice)) {
            return false;
        }
        if (validFrom != null && validFrom.isBefore(timestamp) && validTo.isAfter(timestamp)) {
            return false;
        }
        return true;
    }
}
