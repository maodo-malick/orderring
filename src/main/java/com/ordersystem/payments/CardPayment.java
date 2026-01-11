package com.ordersystem.payments;

public class CardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " +amount+ " using Card");
    }
}
