package com.ordersystem;

import com.ordersystem.logging.ConsoleLogger;
import com.ordersystem.logging.Logger;
import com.ordersystem.order.Order;
import com.ordersystem.payments.DefaultPaymentProvider;
import com.ordersystem.payments.PaymentProvider;
import com.ordersystem.payments.PaymentType;
import com.ordersystem.services.OrderService;
import com.ordersystem.shipping.DefaultShippingProvider;
import com.ordersystem.shipping.ShippingProvider;
import com.ordersystem.shipping.ShippingType;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Logger consoleLogger = new ConsoleLogger();
        PaymentProvider defaultPaymentProvider = new DefaultPaymentProvider();
        ShippingProvider shippingProvider = new DefaultShippingProvider();
        OrderService orderService = new OrderService(consoleLogger, defaultPaymentProvider,shippingProvider);
        Order order1 = new Order("ORD::001", 4000);
        Order order2 = new Order("ORD::002",500);
        Order order3= new Order("ORD::3", 600);

        System.out.println("\n=== ORDER 1 : CARD + STANDARD ===");
        orderService.processOrder(
                PaymentType.CARD,
                ShippingType.STANDARD,
                order1
        );
        System.out.println("\n=== ORDER 2 : PAYPAL + EXPRESS ===");
        orderService.processOrder(
                PaymentType.PAYPAL,
                ShippingType.EXPRESS,
                order2
        );
        System.out.println("\n=== ORDER 3 : CRYPTO + EXPRESS (FAIL) ===");
        orderService.processOrder(
                PaymentType.CRYPTO,
                ShippingType.EXPRESS,
               order3
        );
    }
}
