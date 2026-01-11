package com.ordersystem.shipping;

public class ExpressShipping implements ShippingStrategy{
    @Override
    public void ship(String orderId) {
        System.out.println("Shipping order " + orderId + " via Express Shipping" );
    }
}
