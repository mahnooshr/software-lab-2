import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PolymorphismDemo {
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
        
     
        Payment[] payments = createSamplePayments(customerInfo);
        
     
        PaymentProcessor processor = new PaymentProcessor(paypalGateway);
        
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n===== Payment Processing System =====");
            System.out.println("1. Process payments with PayPal Gateway");
            System.out.println("2. Process payments with Stripe Gateway");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    
                    processor.setGateway(paypalGateway);
                    processAllPayments(processor, payments);
                    break;
                case 2:
                   
                    processor.setGateway(stripeGateway);
                    processAllPayments(processor, payments);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        
        scanner.close();
    }
    
    private static Payment[] createSamplePayments(Map<String, String> customerInfo) {
       
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
        
     
        Map<String, String> bankDetails = new HashMap<>();
        bankDetails.put("account_number", "123456789");
        bankDetails.put("bank_code", "BANK123");
        bankDetails.put("payment_type", "bank_transfer");
        bankDetails.put("payment_endpoint", "transfers");
        
        Payment bankPayment = new BankTransferPayment(200.00, "USD", customerInfo, bankDetails);
        
        return new Payment[] { creditCardPayment, walletPayment, bankPayment };
    }
    
    private static void processAllPayments(PaymentProcessor processor, Payment[] payments) {
        System.out.println("\nProcessing all payments...");
        
        int index = 1;
        for (Payment payment : payments) {
            String paymentType = payment.getClass().getSimpleName();
            System.out.println("\nPayment #" + index + " (" + paymentType + "):");
            
            try {
                Map<String, String> result = processor.processPayment(payment);
                System.out.println("Status: " + result.get("status"));
                System.out.println("Transaction ID: " + result.get("transaction_id"));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            index++;
        }
    }
}