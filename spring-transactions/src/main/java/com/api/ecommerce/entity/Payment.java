package com.api.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column
	private String paymentType;
	@Column
	private String cardName;
	@Column
	private String cardNumber;
	@Column
	private String expiryYear;
	@Column
	private String expiryMonth;
	@Column
	private int cardCvc;
	@Column
	private UUID orderId;
}
