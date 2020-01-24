package com.yugabyte.ycql.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yugabyte.ycql.sample.domain.Product;
import com.yugabyte.ycql.sample.repository.ProductReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

	@Autowired
	private ProductReactiveRepository productReactiveRepository;

	@GetMapping("/products")
	public Flux<Product> getProducts() {
		return productReactiveRepository.findAll();
	}

	@PostMapping("/products/save")
	public Mono<Product> createProductUsingSaveAPI(@RequestBody Product product) {
		return productReactiveRepository.save(product);
	}

	@GetMapping("/products/{productId}")
	public Mono<Product> findProductById(@PathVariable String productId, @RequestBody Product productRequest) {
		return productReactiveRepository.findById(productId);
	}

	@GetMapping("/products/delete/{productId}")
	public Mono<Void> deleteProductById(@PathVariable String productId) {
		return productReactiveRepository.deleteById(productId);
	}

}