import java.util.Map;


public interface PaymentGateway {

    Map<String, String> processPayment(double amount, String currency, 
                                      Map<String, String> customerInfo,
                                      Map<String, String> paymentDetails);
    
 
    Map<String, String> refundPayment(String transactionId);
    
  
    Map<String, String> getTransactionStatus(String transactionId);
}
