package com.sudhakar.springcloud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.sudhakar.springcloud.service.ProductService;

@RestController
@RequestMapping("productapi")
@CrossOrigin(origins="http://localhost:4200")
public class ProductRestController {

	@Autowired
	ProductRepo repo;

	@Autowired
	RestTemplate restTemplate;

	@Value("${couponservice.url}")
	private String couponService;

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	public Product create(@RequestBody Product product) {
		if (null != product.getCouponCode()) {
			Coupon coupon = restTemplate.getForObject(couponService + product.getCouponCode(), Coupon.class);
			product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		}
		return productService.saveProduct(product);
	}

	@GetMapping("/products/{name}")
	public Product getProduct(@PathVariable("name") String name) {
		return productService.getProduct(name);
	}

	@GetMapping("/products")
	public ResponseEntity<?> getAllProducts() {
		List<Product> products = productService.findAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping("product/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		return "Product:" + id + " Deleted Successfully";

	}

}
