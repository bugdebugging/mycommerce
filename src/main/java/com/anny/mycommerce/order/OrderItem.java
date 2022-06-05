package com.anny.mycommerce.order;

import com.anny.mycommerce.common.domain.Money;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Column
    private Long productId;

    @Column
    private int count;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @Builder(builderMethodName = "of")
    public OrderItem(Long productId, int count, Money price) {
        Assert.notNull(productId, "주문 상품의 id는 필수입니다.");
        Assert.state(count > 0, "주문 상품의 개수는 0보다 커야합니다.");
        Assert.notNull(price, "제품의 가격은 필수입니다.");

        this.productId = productId;
        this.count = count;
        this.price = price;
    }
}
