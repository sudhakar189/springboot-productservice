package com.sudhakar.springcloud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sudhakar.springcloud.dto.Coupon;
import com.sudhakar.springcloud.model.Product;
import com.sudhakar.springcloud.repos.ProductRepo;

@RestController
@RequestMapping("productapi")
public class ProductRestController {
	
	@Autowired
	ProductRepo repo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${couponservice.url}")
	private String couponService;
	
	@PostMapping("/products")
	public Product create(@RequestBody Product product) {		
		Coupon coupon=restTemplate.getForObject(couponService + product.getCouponCode() , Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);		
	}
	
	@GetMapping("/products/{name}")
	public Product getProduct(@PathVariable("name") String name) {		
		return repo.findByName(name);
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return repo.findAll();
	}

}
