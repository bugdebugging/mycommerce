package com.anny.mycommerce.common.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Money {
    @Column
    private Long amount;
    private static final Money ZERO = of(0L);

    public static Money of(Long amount) {
        return new Money(amount);
    }

    public Money plus(Money money) {
        return new Money(getAmount() + money.getAmount());
    }

    public Money minus(Money money) {
        return new Money(getAmount() - money.getAmount());
    }

    public Money times(Money money) {
        return new Money(getAmount() * money.getAmount());
    }

    public Money ne() {
        return new Money(-getAmount());
    }
}
