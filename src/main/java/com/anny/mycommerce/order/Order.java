package com.anny.mycommerce.order;

import com.anny.mycommerce.common.domain.Address;
import com.anny.mycommerce.common.domain.Money;
import com.anny.mycommerce.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order(User orderer, Money price, Address shippingAddress, PaymentType paymentType) {
        this.orderer = orderer;
        this.price = price;
        this.shippingAddress = shippingAddress;
        this.status = OrderStatus.PAYMENT_WAITING;
        this.paymentType = paymentType;
    }

    public static Order of(User orderer, Money price, Address shippingAddress, PaymentType paymentType) {
        return new Order(orderer, price, shippingAddress, paymentType);
    }
}
