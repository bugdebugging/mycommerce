package com.anny.mycommerce.order.domain;

import com.anny.mycommerce.common.domain.Address;
import com.anny.mycommerce.common.domain.Money;
import com.anny.mycommerce.user.domain.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderer_id")
    private User orderer;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "basicAddress", column = @Column(name = "shipping_basic_address")),
            @AttributeOverride(name = "detailAddress", column = @Column(name = "shipping_detail_address")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "shipping_zip_code"))})
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder(builderMethodName = "of")
    public Order(User orderer, Address shippingAddress, PaymentType paymentType, List<OrderItem> orderItems) {
        Assert.notNull(orderer, "주문의 주문자는 필수입니다.");
        Assert.notNull(shippingAddress, "주문의 배송지는 필수입니다.");
        Assert.notNull(paymentType, "주문의 결제 방법은 필수입니다.");
        Assert.notNull(orderItems, "주문 아이템은 필수입니다.");

        this.orderer = orderer;
        this.price = Money.ZERO;
        this.shippingAddress = shippingAddress;
        this.status = OrderStatus.PAYMENT_WAITING;
        this.paymentType = paymentType;
        this.orderItems = orderItems;
    }
}
