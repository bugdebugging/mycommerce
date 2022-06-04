package com.anny.mycommerce.order;

import com.anny.mycommerce.common.domain.Money;
import com.anny.mycommerce.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column
    private int count;

    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    public OrderItem(Order order, Product product, int count, Money price) {
        this.order = order;
        this.product = product;
        this.count = count;
        this.price = price;
    }

    public static OrderItem of(Order order, Product product, int count, Money price) {
        return new OrderItem(order, product, count, price);
    }
}
