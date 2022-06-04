package com.anny.mycommerce.cart.domain;

import com.anny.mycommerce.product.domain.Product;
import com.anny.mycommerce.user.domain.User;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "shopping_carts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column
    private int count;

    @Builder(builderMethodName = "of")
    public ShoppingCartItem(User user, Product product, int count) {
        Assert.notNull(user, "카트의 사용자는 필수입니다.");
        Assert.notNull(product, "카트에 들어갈 제품은 필수입니다.");
        Assert.state(count > 0, "제품의 수량은 0 이상이어야합니다.");
        this.user = user;
        this.product = product;
        this.count = count;
    }
}
