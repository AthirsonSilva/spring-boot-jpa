package com.api.ecommerce.payload;

import com.api.ecommerce.entity.Order;
import com.api.ecommerce.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
	private Order order;
	private Payment payment;
}
