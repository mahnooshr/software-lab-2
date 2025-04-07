import java.util.Map;

public class CreditCardGateway implements PaymentGateway {
    private String endpoint;

    public CreditCardGateway(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> processPayment(Payment payment) {
        System.out.println("Processing credit card via endpoint: " + endpoint);
        return payment.processPayment();
    }

    @Override
    public boolean refundPayment(String transactionId) {
        System.out.println("Refunding credit card transaction: " + transactionId);
        return true;
    }

    @Override
    public String getTransactionStatus(String transactionId) {
        System.out.println("Checking status for credit card: " + transactionId);
        return "pending";
    }
}
