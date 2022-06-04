package com.anny.mycommerce.user.domain;

import com.anny.mycommerce.common.domain.Address;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phone;

    @Embedded
    private Address address;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder(builderMethodName = "of")
    public User(String email, String password, String phone, Address address) {
        Assert.hasText(email, "이메일은 필수입니다.");
        Assert.hasText(password, "비밀번호는 필수입니다.");
        Assert.hasText(phone, "휴대전화 번호는 필수입니다.");
        Assert.notNull(address, "주소는 필수입니다.");

        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    @Builder(builderClassName = "forTest", builderMethodName = "forTest")
    public User(Long id, String email, String password, String phone, Address address, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email == null ? "testEmail@test.com" : email;
        this.password = password == null ? "testPassword" : password;
        this.phone = phone == null ? "010-0100-1010" : phone;
        this.address = address == null ? Address.of("test", "test", "11111") : address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
