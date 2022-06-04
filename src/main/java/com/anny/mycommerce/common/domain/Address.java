package com.anny.mycommerce.common.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Address {
    @Column
    private String basicAddress;

    @Column
    private String detailAddress;

    @Column
    private String zipCode;

    public static Address of(String basicAddress, String detailAddress, String zipCode) {
        return new Address(basicAddress, detailAddress, zipCode);
    }
}
