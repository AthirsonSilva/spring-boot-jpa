package com.api.ecommerce.controller;

import com.api.ecommerce.payload.OrderRequest;
import com.api.ecommerce.payload.OrderResponse;
import com.api.ecommerce.services.OrderService;
import com.api.ecommerce.services.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	private final OrderServiceImpl orderService;

	@Autowired
	public OrderController(OrderServiceImpl orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
		return ResponseEntity.ok(orderService.placeOrder(orderRequest));
	}
}
