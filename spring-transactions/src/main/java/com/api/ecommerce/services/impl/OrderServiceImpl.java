package com.api.ecommerce.services.impl;

import com.api.ecommerce.entity.Order;
import com.api.ecommerce.entity.Payment;
import com.api.ecommerce.exception.PaymentException;
import com.api.ecommerce.payload.OrderRequest;
import com.api.ecommerce.payload.OrderResponse;
import com.api.ecommerce.repository.OrderRepository;
import com.api.ecommerce.repository.PaymentRepository;
import com.api.ecommerce.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final PaymentRepository paymentRepository;

	public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
		this.orderRepository = orderRepository;
		this.paymentRepository = paymentRepository;
	}

	@Override
	@Transactional
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		Order order = orderRequest.getOrder();

		order.setStatus("IN PROGRESS");
		order.setOrderTackingNumber(UUID.randomUUID().toString());

		orderRepository.save(order);

		Payment payment = orderRequest.getPayment();

		if (!payment.getPaymentType().equals("DEBIT") && !payment.getPaymentType().equals("CREDIT")) {
			throw new PaymentException("Payment type not supported", HttpStatus.BAD_REQUEST);
		}

		payment.setOrderId(order.getId());

		paymentRepository.save(payment);

		OrderResponse orderResponse = new OrderResponse();

		orderResponse.setOrderTrackingNumber(order.getOrderTackingNumber());
		orderResponse.setStatus(order.getStatus());
		orderResponse.setMessage("Order placed successfully");

		return orderResponse;
	}
}
