package com.ordersystem.shipping;

public class StandardShipping implements ShippingStrategy{
    @Override
    public void ship(String orderId) {
        System.out.println("Shipping order " + orderId + " via Standard Shipping" );
    }
}
