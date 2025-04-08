import java.util.HashMap;
import java.util.Map;

import GatewayClasses.PayPalGateway;
import GatewayClasses.PaymentGateway;
import GatewayClasses.StripeGateway;

public class PaymentClient {
    public static void main(String[] args) {
      
        Map<String, String> customerInfo = new HashMap<>();
        customerInfo.put("name", "John Doe");
        customerInfo.put("email", "john.doe@example.com");
        
        
        Map<String, String> paypalConfig = new HashMap<>();
        paypalConfig.put("paypal_process_endpoint", "https://api.paypal.com/v1/payments/");
        paypalConfig.put("paypal_refund_endpoint", "https://api.paypal.com/v1/refunds/");
        paypalConfig.put("paypal_get_status_endpoint", "https://api.paypal.com/v1/status/");
        
        Map<String, String> stripeConfig = new HashMap<>();
        stripeConfig.put("stripe_process_endpoint", "https://api.stripe.com/v1/charges/");
        stripeConfig.put("stripe_refund_endpoint", "https://api.stripe.com/v1/refunds/");
        stripeConfig.put("stripe_get_status_endpoint", "https://api.stripe.com/v1/status/");
        
        
        PaymentGateway paypalGateway = new PayPalGateway(paypalConfig);
        PaymentGateway stripeGateway = new StripeGateway(stripeConfig);
        
       
        Map<String, String> creditCardDetails = new HashMap<>();
        creditCardDetails.put("card_number", "4111111111111111");
        creditCardDetails.put("expiry", "12/25");
        creditCardDetails.put("cvv", "123");
        creditCardDetails.put("payment_type", "credit_card");
        creditCardDetails.put("payment_endpoint", "credit_cards");
        
        Payment creditCardPayment = new CreditCardPayment(100.00, "USD", customerInfo, creditCardDetails);
       
        Map<String, String> walletDetails = new HashMap<>();
        walletDetails.put("wallet_id", "wallet_12345");
        walletDetails.put("payment_type", "digital_wallet");
        walletDetails.put("payment_endpoint", "wallets");
        
        Payment walletPayment = new DigitalWalletPayment(150.00, "USD", customerInfo, walletDetails);
        
       
        System.out.println("\n===== Processing Credit Card Payment with PayPal =====");
        if (creditCardPayment.validatePayment()) {
            Map<String, String> paypalResult = creditCardPayment.processPayment(paypalGateway);
            System.out.println("PayPal Payment Status: " + paypalResult.get("status"));
            System.out.println("PayPal Transaction ID: " + paypalResult.get("transaction_id"));
        } else {
            System.out.println("Credit card payment validation failed");
        }
        
        System.out.println("\n===== Processing Credit Card Payment with Stripe =====");
        if (creditCardPayment.validatePayment()) {
            Map<String, String> stripeResult = creditCardPayment.processPayment(stripeGateway);
            System.out.println("Stripe Payment Status: " + stripeResult.get("status"));
            System.out.println("Stripe Transaction ID: " + stripeResult.get("transaction_id"));
        } else {
            System.out.println("Credit card payment validation failed");
        }
        
        
        System.out.println("\n===== Processing Digital Wallet Payment with PayPal =====");
        if (walletPayment.validatePayment()) {
            Map<String, String> paypalResult = walletPayment.processPayment(paypalGateway);
            System.out.println("PayPal Payment Status: " + paypalResult.get("status"));
            System.out.println("PayPal Transaction ID: " + paypalResult.get("transaction_id"));
        } else {
            System.out.println("Digital wallet payment validation failed");
        }
        
        System.out.println("\n===== Processing Digital Wallet Payment with Stripe =====");
        if (walletPayment.validatePayment()) {
            Map<String, String> stripeResult = walletPayment.processPayment(stripeGateway);
            System.out.println("Stripe Payment Status: " + stripeResult.get("status"));
            System.out.println("Stripe Transaction ID: " + stripeResult.get("transaction_id"));
        } else {
            System.out.println("Digital wallet payment validation failed");
        }
    }
}