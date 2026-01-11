package com.ordersystem.shipping;

public interface ShippingProvider {
    ShippingStrategy get(ShippingType type);
}
