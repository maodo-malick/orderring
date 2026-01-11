package com.ordersystem.services;

import com.ordersystem.factory.PaymentFactory;
import com.ordersystem.factory.ShippingFactory;
import com.ordersystem.logging.Logger;
import com.ordersystem.order.Order;
import com.ordersystem.order.OrderStatus;
import com.ordersystem.payments.DefaultPaymentProvider;
import com.ordersystem.payments.PaymentProvider;
import com.ordersystem.payments.PaymentStrategy;
import com.ordersystem.payments.PaymentType;
import com.ordersystem.shipping.ExpressShipping;
import com.ordersystem.shipping.ShippingProvider;
import com.ordersystem.shipping.ShippingStrategy;
import com.ordersystem.shipping.ShippingType;

public class OrderService  {
    private int nbrMax;

    private final Logger log;
    private final PaymentProvider paymentProvider;
    private final ShippingProvider shippingProvider;

    public OrderService(Logger log, PaymentProvider paymentProvider , ShippingProvider shippingProvider){
        this.paymentProvider= paymentProvider;
        this.shippingProvider = shippingProvider;
        this.log = log;
    }

    public boolean processPayment(PaymentType type, double montant){

            if (montant>1000){
                log.error("Amount of Payment not allowed");
                return false;
            }
            PaymentStrategy paymentStrategy =this.paymentProvider.get(type);
            paymentStrategy.pay(montant);
            log.info("Payment proceeded successfully !");
            return true;

    }
   public void processOrder( PaymentType paymentType,ShippingType shippingType, Order order){
        boolean paid = processPayment(paymentType, order.getAmount());
        if(!paid){
            order.setStatus(OrderStatus.PAYMENT_FAILED);
            log.error("Order " + order.getOrderId() + "failed");
            return;
        }
        order.setStatus(OrderStatus.PAID);
        ShippingStrategy shippingStrategy = shippingProvider.get(shippingType);
        shippingStrategy.ship(order.getOrderId());
        order.setStatus(OrderStatus.SHIPPED);
        log.info("Order " + order.getOrderId() + "shipped successfully !");
   }



}
