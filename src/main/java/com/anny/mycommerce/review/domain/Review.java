package com.anny.mycommerce.review.domain;

import com.anny.mycommerce.common.domain.Image;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
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

    @Builder(builderMethodName = "of")
    public Review(Long writerId, Long productId, String content, int rate, List<Image> images) {
        Assert.notNull(writerId, "리뷰 작성자의 id는 필수입니다.");
        Assert.notNull(productId, "리뷰 상품의 id는 필수입니다.");
        Assert.notNull(content, "리뷰의 내용글은 null일 수 없습니다.");
        Assert.state(rate>=0 && rate<=5,"리뷰 점수는 0~5점만 가능합니다.");
        Assert.notNull(images,"리뷰의 이미지 리스트는 null일 수 없습니다.");

        this.writerId = writerId;
        this.productId = productId;
        this.content = content;
        this.rate = rate;
        this.images = images;
    }
}
