package com.ordersystem.factory;

import com.ordersystem.payments.*;

import java.util.HashMap;
import java.util.Map;

public class PaymentFactory {
    private static final Map<PaymentType, PaymentStrategy> registry = new HashMap<>();
    static{
        registry.put(PaymentType.CARD, new CardPayment());
        registry.put(PaymentType.PAYPAL, new PaypalPayment());
        registry.put(PaymentType.CRYPTO, new CryptoPayment());
    }
    public static PaymentStrategy create(PaymentType type) {
        PaymentStrategy strategy = registry.get(type);
        if (strategy ==null) {
            throw new IllegalArgumentException("No PaymentStrategy registered for type: " + type
            );
        }
        return  strategy;
    }
}
