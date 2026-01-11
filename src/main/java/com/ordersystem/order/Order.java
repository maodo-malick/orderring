package com.ordersystem.order;

public class Order {
    public String getOrderId() {
        return orderId;
    }

    private final String orderId;
    private final double amount;
    private OrderStatus status;

    public Order(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = OrderStatus.CREATED;
    }

    public double getAmount() {
        return amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
