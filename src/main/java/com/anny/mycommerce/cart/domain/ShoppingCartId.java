package com.anny.mycommerce.cart.domain;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShoppingCartId implements Serializable {
    @Column
    private Long userId;
    @Column
    private Long productId;

    @Builder(builderMethodName = "of")
    public ShoppingCartId(Long userId, Long productId) {
        Assert.notNull(userId, "shoppingCartId의 userId는 필수입니다.");
        Assert.notNull(productId, "shoppingCartId의 productId는 필수입니다.");

        this.userId = userId;
        this.productId = productId;
    }
}
