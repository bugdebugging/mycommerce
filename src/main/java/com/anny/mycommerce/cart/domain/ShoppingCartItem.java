package com.anny.mycommerce.cart.domain;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "shopping_carts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class ShoppingCartItem {
    @EmbeddedId
    private ShoppingCartId id;

    @Column
    private int count;

    @Builder(builderMethodName = "of")
    public ShoppingCartItem(Long userId, Long productId, int count) {
        Assert.notNull(userId, "카트의 사용자의 id는 필수입니다.");
        Assert.notNull(productId, "카트에 들어갈 상품의 id는 필수입니다.");
        Assert.state(count > 0, "제품의 수량은 0 이상이어야합니다.");

        this.id= ShoppingCartId.of()
                .userId(userId)
                .productId(productId)
                .build();
        this.count = count;
    }
}
