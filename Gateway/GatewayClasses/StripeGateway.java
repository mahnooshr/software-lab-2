package GatewayClasses;
import java.util.Date;
import java.util.Map;

public class StripeGateway implements PaymentGateway{
    
    private Map<String, String> config;

    public StripeGateway(Map<String, String> config){
        this.config = config;
    }

    public String processPayment(double amount, String currency, 
                                      Map<String, String> customerInfo,
                                      Map<String, String> paymentDetails){

        String paymentEndpoint = paymentDetails.get("payment_endpoint");
        String baseUrl = this.config.get("stripe_process_endpoint");
        System.out.println("Connecting to StripeGateway at " + baseUrl + paymentEndpoint);

        String paymentType = paymentDetails.get("payment_type");
        String transactionId = paymentType + new Date().getTime();
        
        System.out.println("Processing payment for " + customerInfo.get("name"));
        return transactionId;
    }
    
    public void refundPayment(Map<String, String> paymentDetails, String transactionId){
        String paymentEndpoint = paymentDetails.get("payment_endpoint");
        String baseUrl = this.config.get("stripe_refund_endpoint");
        System.out.println("Refund payment with transaction id" + transactionId + " to StripeGateway at " + baseUrl + paymentEndpoint);
    }
    
    public Map<String, String> getTransactionStatus(Map<String, String> paymentDetails, String transactionId){
        String paymentEndpoint = paymentDetails.get("payment_endpoint");
        String baseUrl = this.config.get("stripe_get_status_endpoint");
        System.out.println("Get status of payment with transaction id" + transactionId + " to StripeGateway at " + baseUrl + paymentEndpoint);
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}