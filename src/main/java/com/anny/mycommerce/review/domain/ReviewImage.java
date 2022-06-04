package com.anny.mycommerce.review.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "review_imgs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id")
    private Review review;

    @Column
    private String path;

    public ReviewImage(Review review, String path) {
        this.review = review;
        this.path = path;
    }

    public static ReviewImage of(Review review, String path) {
        return new ReviewImage(review, path);
    }
}
