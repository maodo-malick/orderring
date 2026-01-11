package com.ordersystem.payments;

import com.ordersystem.factory.PaymentFactory;

public class DefaultPaymentProvider implements  PaymentProvider{
    @Override
    public PaymentStrategy get(PaymentType type) {
       return PaymentFactory.create(type);
    }
}
