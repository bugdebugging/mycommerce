package com.anny.mycommerce.user.domain;

import com.anny.mycommerce.common.domain.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    private User(String email, String password, String phone, Address address) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public static User newUser(String email, String password, String phone, Address address) {
        return new User(email, password, phone, address);
    }
}
