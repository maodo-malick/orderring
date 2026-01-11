package com.ordersystem.factory;

import com.ordersystem.shipping.ExpressShipping;
import com.ordersystem.shipping.ShippingStrategy;
import com.ordersystem.shipping.ShippingType;
import com.ordersystem.shipping.StandardShipping;

import java.util.HashMap;
import java.util.Map;

public class ShippingFactory {
    private static final Map<ShippingType, ShippingStrategy> registry =new HashMap<>();
    static {
        registry.put(ShippingType.STANDARD, new StandardShipping());
        registry.put(ShippingType.EXPRESS, new ExpressShipping());
    }
    public static ShippingStrategy create(ShippingType type){
       ShippingStrategy strategy= registry.get(type);
       if (strategy == null){
           throw new IllegalArgumentException("No ShippingStrategy registered for type: " + type);
       }
       return strategy;
    }
}
