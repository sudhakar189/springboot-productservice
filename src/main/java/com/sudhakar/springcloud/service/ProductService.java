package com.sudhakar.springcloud.service;

import java.util.List;

import com.sudhakar.springcloud.model.Product;

public interface ProductService {

	Product saveProduct(Product product);

	List<Product> findAllProducts();

	Product getProduct(String name);

	void deleteProduct(Long id);

}
