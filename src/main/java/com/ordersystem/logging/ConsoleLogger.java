package com.ordersystem.logging;

public class ConsoleLogger implements Logger{
    @Override
    public void info(String msg) {
        System.out.println("[INFO]" + msg);
    }

    @Override
    public void error(String msg) {
        System.out.println("[ERROR]" + msg);
    }
}
