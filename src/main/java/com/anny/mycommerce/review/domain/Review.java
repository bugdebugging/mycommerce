package com.anny.mycommerce.review.domain;

import com.anny.mycommerce.common.domain.Image;
import com.anny.mycommerce.product.domain.Product;
import com.anny.mycommerce.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long writerId;

    @Column
    private Long productId;

    @Column
    private String content;

    @Column
    private int rate;

    @ElementCollection
    @CollectionTable(name = "review_imgs", joinColumns = @JoinColumn(name = "review_id"))
    private List<Image> images;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Review(Long writerId, Long productId, String content, int rate, List<Image> images) {
        this.writerId = writerId;
        this.productId = productId;
        this.content = content;
        this.rate = rate;
        this.images = images;
    }
}
