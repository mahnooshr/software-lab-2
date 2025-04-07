import java.util.Map;

public class PaymentProcessor {
    private PaymentGateway gateway;

    public PaymentProcessor(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void executePayment(Payment payment) {
        if (payment.validatePayment()) {
            Map<String, String> result = gateway.processPayment(payment);
            System.out.println("Payment status: " + result.get("status"));
        } else {
            System.out.println("Invalid payment details.");
        }
    }
}
