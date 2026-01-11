package com.ordersystem.payments;

public interface PaymentProvider {
    PaymentStrategy get(PaymentType type);
}
