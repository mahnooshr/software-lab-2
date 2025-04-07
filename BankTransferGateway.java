import java.util.Map;

public class BankTransferGateway implements PaymentGateway {
    private String endpoint;

    public BankTransferGateway(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> processPayment(Payment payment) {
        System.out.println("Processing bank transfer via endpoint: " + endpoint);
        return payment.processPayment();
    }

    @Override
    public boolean refundPayment(String transactionId) {
        System.out.println("Refunding bank transfer transaction: " + transactionId);
        return true;
    }

    @Override
    public String getTransactionStatus(String transactionId) {
        System.out.println("Checking status for bank transfer: " + transactionId);
        return "completed";
    }
}
