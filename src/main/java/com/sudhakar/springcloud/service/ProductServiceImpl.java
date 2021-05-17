package com.sudhakar.springcloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudhakar.springcloud.model.Product;
import com.sudhakar.springcloud.repos.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepo productRepo;

	@Override
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public List<Product> findAllProducts() {
				return productRepo.findAll();
	}

	@Override
	public Product getProduct(String name) {
		// TODO Auto-generated method stub
		return productRepo.findByName(name);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
		
	}

}
