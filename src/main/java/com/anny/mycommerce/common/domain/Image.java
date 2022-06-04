package com.anny.mycommerce.common.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Image {
    @Column
    private String path;

    public static Image of(String path) {
        return new Image(path);
    }
}
