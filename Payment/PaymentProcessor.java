import java.util.Map;

import GatewayClasses.PaymentGateway;

public class PaymentProcessor {
    private PaymentGateway gateway;

    public PaymentProcessor(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public Map<String, String> processPayment(Payment payment) {
        if (!payment.validatePayment()) {
            throw new IllegalArgumentException("Payment validation failed");
        }

        return payment.processPayment(this.gateway);
    }
}