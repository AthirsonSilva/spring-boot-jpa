package com.api.ecommerce.services;

import com.api.ecommerce.payload.OrderRequest;
import com.api.ecommerce.payload.OrderResponse;

public interface OrderService {
	OrderResponse placeOrder(OrderRequest orderRequest);
}
