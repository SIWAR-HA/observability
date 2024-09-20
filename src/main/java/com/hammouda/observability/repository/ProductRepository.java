package com.hammouda.observability.repository;

import com.hammouda.observability.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
