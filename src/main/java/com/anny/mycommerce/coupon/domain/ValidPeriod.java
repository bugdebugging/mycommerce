package com.anny.mycommerce.coupon.domain;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidPeriod {
    @Column
    private LocalDateTime validFrom;
    @Column
    private LocalDateTime validTo;

    @Builder(builderMethodName = "of")
    public ValidPeriod(LocalDateTime validFrom, LocalDateTime validTo) {
        Assert.notNull(validFrom, "유효기간 시작시간은 필수입니다.");
        Assert.notNull(validTo, "유효기간 종료시간은 필수입니다.");
        Assert.state(validFrom.isBefore(validTo), "유효시간의 시작시간은 끝시간보다 과거여야합니다.");

        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public boolean isValidDate(LocalDateTime timestamp) {
        return validFrom.isBefore(timestamp) && validTo.isAfter(timestamp);
    }
}
