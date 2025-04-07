import java.util.Map;

    public class CreditCardPaymentGateway implements PaymentGateway {
    private Map<String, String> config;

    public CreditCardPaymentGateway(Map<String, String> config) {
        this.config = config;
    }


    @Override
   public Map<String, String> processPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
    
        System.out.println("Connecting to Credit Card API at " + this.config.get("credit_card_endpoint"));
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing credit card payment for " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
        
    @Override
    public Map<String, String> refundPayment(String transactionId) {
        
        System.out.println("Refunding credit card payment with transaction ID " + transactionId);
        return Map.of("status", "success", "message", "Refund successful");
    }

    @Override
    public Map<String, String> getTransactionStatus(String transactionId) {
        
        System.out.println("Getting transaction status for transaction ID " + transactionId);
        return Map.of("status", "success", "transaction_id", transactionId, "status_message", "Payment successful");
    }
}
