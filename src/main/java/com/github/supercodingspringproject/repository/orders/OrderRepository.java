package com.github.supercodingspringproject.repository.orders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findAllByGeneralUserId(Integer id, Pageable pageable);
}