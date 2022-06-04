package com.anny.mycommerce.product.domain;

import com.anny.mycommerce.category.domain.Category;
import com.anny.mycommerce.common.domain.Money;
import com.anny.mycommerce.store.domain.Store;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @Column
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProductImage> images;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder(builderMethodName = "of")
    public Product(String name, String description, Money price, int stock, Store store, Category category, List<ProductImage> images) {
        Assert.hasText(name, "상품 이름은 필수입니다.");
        Assert.hasText(description, "상품 설명은 필수입니다.");
        Assert.notNull(price, "상품 가격은 필수입니다.");
        Assert.notNull(store,"상품의 지정 스토어는 필수입니다.");
        Assert.notNull(category,"상품의 카테고리는 필수입니다.");
        Assert.notNull(images,"상품 이미지 배열은 NULL 일 수 없습니다.");

        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.store = store;
        this.category = category;
        this.images = images;
        this.images.stream().forEach(image->image.assignProduct(this));
    }
}
