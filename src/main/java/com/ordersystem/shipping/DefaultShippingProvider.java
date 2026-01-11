package com.ordersystem.shipping;

import com.ordersystem.factory.ShippingFactory;

public class DefaultShippingProvider implements  ShippingProvider{
    @Override
    public ShippingStrategy get(ShippingType type) {
        return ShippingFactory.create(type);
    }
}
