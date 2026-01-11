package com.ordersystem.payments;

public class CryptoPayment  implements PaymentStrategy{

    @Override
    public void pay(double amount) {
        System.out.println("Paying " +amount+ " using Crypto");
    }
}
