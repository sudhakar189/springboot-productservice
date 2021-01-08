package com.sudhakar.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudhakar.springcloud.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

	Product findByName(String name);

}
