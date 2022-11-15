package com.example.bbsbuild.dockertest.repository;

import com.example.bbsbuild.dockertest.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
