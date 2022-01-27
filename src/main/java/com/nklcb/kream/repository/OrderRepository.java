package com.nklcb.kream.repository;

import com.nklcb.kream.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
