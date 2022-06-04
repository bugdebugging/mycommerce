package com.anny.mycommerce.cart.domain;

import com.anny.mycommerce.product.domain.Product;
import com.anny.mycommerce.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "shopping_carts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public ShoppingCartItem(User user, Product product, int count) {
        this.user = user;
        this.product = product;
        this.count = count;
    }

    public static ShoppingCartItem of(User user, Product product, int count) {
        return new ShoppingCartItem(user, product, count);
    }
}
