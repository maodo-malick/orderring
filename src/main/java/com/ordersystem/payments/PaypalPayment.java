package com.ordersystem.payments;

public class PaypalPayment  implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Paying " +amount+ " using Paypal");
    }
}
