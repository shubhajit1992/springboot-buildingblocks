package com.shubhajit.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubhajit.restservices.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}