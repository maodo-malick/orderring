package com.ordersystem.services;

import com.ordersystem.logging.Logger;
import com.ordersystem.order.Order;
import com.ordersystem.order.OrderStatus;
import com.ordersystem.payments.*;
import com.ordersystem.shipping.ShippingProvider;
import com.ordersystem.shipping.ShippingStrategy;
import com.ordersystem.shipping.ShippingType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceTest {
    public class FakeLogger implements Logger{
      public List<String> messages = new ArrayList<>();
        @Override
        public void info(String msg) {
         messages.add("[INFO] "+msg);
        }

        @Override
        public void error(String msg) {
        messages.add("[ERROR] "+msg);
        }
    }
    public class FakePaymentSuccess implements PaymentStrategy{
        public boolean paid = false;
        @Override
        public void pay(double amount) {
            paid = true;
        }

    }
    public class FakePaymentProvider implements PaymentProvider {
        private PaymentStrategy strategy;
             public FakePaymentProvider(PaymentStrategy strategy){
                 this.strategy = strategy;
             }
        @Override
        public PaymentStrategy get(PaymentType type) {
           return strategy;
        }
    }
    public class FakeShippingSucess implements ShippingStrategy{
        public boolean shipped = false;
        @Override
        public void ship(String orderId) {
            shipped = true;
        }
    }

    public class FakeShippingProvider implements ShippingProvider{
        private ShippingStrategy strategy;
        public FakeShippingProvider(ShippingStrategy strategy){
            this.strategy = strategy;
        }

        @Override
        public ShippingStrategy get(ShippingType type) {
            return strategy;
        }
    }


    @Test
    void testProcessPayment_Success(){
        FakeLogger fakeLogger = new FakeLogger();
        FakePaymentSuccess fakePaymentSuccess = new FakePaymentSuccess();
        FakeShippingSucess fakeShippingSucess= new FakeShippingSucess();
        FakePaymentProvider fakePaymentProvider = new FakePaymentProvider(fakePaymentSuccess);
        FakeShippingProvider fakeShippingProvider = new FakeShippingProvider(fakeShippingSucess);
        OrderService orderService = new OrderService(fakeLogger,fakePaymentProvider,fakeShippingProvider);
        boolean result = orderService.processPayment(PaymentType.CARD, 500);

        assertTrue(result, "Le paiement doit réussir");
        assertTrue(fakePaymentSuccess.paid, "Le paiement a bien été exécuté");
        assertTrue(fakeLogger.messages.contains("[INFO] Payment proceeded successfully !"));
    }
    @Test
    void testFailedPayment_amountToohight(){
        FakeLogger fakeLogger = new FakeLogger();
        FakePaymentSuccess fakepayment = new FakePaymentSuccess();
        FakeShippingSucess fakeShippingSucess= new FakeShippingSucess();
        FakeShippingProvider fakeShippingProvider = new FakeShippingProvider(fakeShippingSucess);
        FakePaymentProvider fakePaymentProvider = new FakePaymentProvider(fakepayment);
        OrderService orderService = new OrderService(fakeLogger,fakePaymentProvider,fakeShippingProvider);
        double amount = 1500;
        boolean result = orderService.processPayment(PaymentType.CARD,amount);
        assertFalse(result,"Le paiement doit échouer pour un montant trop élevé");
        assertFalse(fakepayment.paid,"Le paiement n'a pas été exécuté");
        assertTrue(fakeLogger.messages.contains("[ERROR] Amount of Payment not allowed"),
                "Le logger doit enregistrer l'erreur de montant");
    }
    @Test
    void testProcessOrder_success() {
        // Création des fakes
        FakeLogger fakeLogger = new FakeLogger();
        FakePaymentSuccess fakePayment = new FakePaymentSuccess();
        FakePaymentProvider fakePaymentProvider = new FakePaymentProvider(fakePayment);
        FakeShippingSucess fakeShippingSucess= new FakeShippingSucess();
        FakeShippingProvider fakeShippingProvider = new FakeShippingProvider(fakeShippingSucess);
        // Création de l'OrderService avec les fakes
        OrderService orderService = new OrderService(fakeLogger, fakePaymentProvider,fakeShippingProvider);

        // Création d'une commande
        Order order = new Order("ORDER-1", 500);

        // Appel de la méthode à tester
        orderService.processOrder(
                PaymentType.CARD,
                ShippingType.EXPRESS,
                order
        );

        // Vérifications
        assertEquals(OrderStatus.SHIPPED, order.getStatus(), "L'order doit être marqué SHIPPED");
        assertTrue(fakePayment.paid, "Le paiement a bien été effectué");
        assertTrue(
                fakeLogger.messages.stream().anyMatch(m -> m.contains("shipped successfully")),
                "Le logger doit contenir un message de shipping"
        );
        assertTrue(fakeShippingSucess.shipped, " commande  expedié vers le destinataire");
    }

    @Test
    void testProcessOrder_paymentFailed() {
        // Création des fakes
        FakeLogger fakeLogger = new FakeLogger();
        FakePaymentSuccess fakePayment = new FakePaymentSuccess();
        FakePaymentProvider fakePaymentProvider = new FakePaymentProvider(fakePayment);
        FakeShippingSucess fakeShippingSucess= new FakeShippingSucess();
        FakeShippingProvider fakeShippingProvider = new FakeShippingProvider(fakeShippingSucess);
        // Création de l'OrderService avec les fakes
        OrderService orderService = new OrderService(fakeLogger, fakePaymentProvider,fakeShippingProvider);

        // Création d'une commande avec montant trop élevé
        Order order = new Order("ORDER-2", 1500);

        // Appel de la méthode à tester
        orderService.processOrder(
                PaymentType.CARD,
                ShippingType.EXPRESS,
                order
        );

        // Vérifications
        assertEquals(OrderStatus.PAYMENT_FAILED, order.getStatus(), "L'order doit être marqué PAYMENT_FAILED");
        assertFalse(fakePayment.paid, "Le paiement ne doit pas être effectué");
        assertTrue(
                fakeLogger.messages.stream().anyMatch(m -> m.contains("failed")),
                "Le logger doit contenir un message d'erreur"
        );
        assertFalse(fakeShippingSucess.shipped ,"Echec de l'expediton ");

    }


}
