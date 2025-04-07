import java.util.Map;

public class DigitalWalletGateway implements PaymentGateway {
    private String endpoint;

    public DigitalWalletGateway(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> processPayment(Payment payment) {
        System.out.println("Processing digital wallet via endpoint: " + endpoint);
        return payment.processPayment();
    }

    @Override
    public boolean refundPayment(String transactionId) {
        System.out.println("Refunding digital wallet transaction: " + transactionId);
        return true;
    }

    @Override
    public String getTransactionStatus(String transactionId) {
        System.out.println("Checking status for digital wallet: " + transactionId);
        return "success";
    }
}
