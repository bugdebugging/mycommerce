package com.anny.mycommerce.cart.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
}
