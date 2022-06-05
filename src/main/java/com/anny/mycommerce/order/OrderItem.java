package com.anny.mycommerce.order;

import com.anny.mycommerce.common.domain.Money;
import com.anny.mycommerce.product.domain.Product;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
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

    @Builder(builderMethodName = "of")
    public OrderItem(Product product, int count, Money price) {
        Assert.notNull(product, "주문 상품은 필수입니다.");
        Assert.state(count > 0, "주문 상품의 개수는 0보다 커야합니다.");
        Assert.notNull(price, "제품의 가격은 필수입니다.");

        this.product = product;
        this.count = count;
        this.price = price;
    }

    public void assignOrder(Order order) {
        this.order = order;
    }
}
