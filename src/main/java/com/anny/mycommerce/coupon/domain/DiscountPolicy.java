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

    @Embedded
    private ValidPeriod period;

    @Builder(builderMethodName = "of")
    public DiscountPolicy(Money limitPrice, ValidPeriod period) {
        Assert.notNull(limitPrice, "할인 가격은 필수입니다.");
        Assert.notNull(period, "유효기간은 필수입니다.");

        this.limitPrice = limitPrice;
        this.period = period;
    }

    public boolean canApplyCouponOn(Money money, LocalDateTime timestamp) {
        if (Money.max(limitPrice, money).equals(limitPrice)) {
            return false;
        }
        if (period.isValidDate(timestamp)) {
            return false;
        }
        return true;
    }
}
