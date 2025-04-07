import java.util.Map;

public interface PaymentGateway {
    Map<String, String> processPayment(Payment payment);
    boolean refundPayment(String transactionId);
    String getTransactionStatus(String transactionId);
}
