package com.anny.mycommerce.product.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "product_imgs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private String path;

    public ProductImage(String path) {
        this.product = product;
        this.path = path;
    }

    public void assignProduct(Product product) {
        this.product = product;
    }

    public static ProductImage of(String path) {
        return new ProductImage(path);
    }
}
