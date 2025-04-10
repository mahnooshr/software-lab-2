package GatewayProviders;
import java.util.HashMap;

import GatewayClasses.PayPalGateway;
import GatewayClasses.PaymentGateway;

public class PayPalGatewayGetter implements GatewayGetter{
    private PayPalGateway paypalGateway;

    public PayPalGatewayGetter() {
        HashMap<String, String> paypalConfig = new HashMap<>();

        this.setConfig("paypal_process_endpoint", "https://api.paypal.com/v1/payments/", paypalConfig);
        this.setConfig("paypal_refund_endpoint", "https://api.paypal.com/v1/refunds/", paypalConfig);
        this.setConfig("paypal_get_status_endpoint", "https://api.paypal.com/v1/status/", paypalConfig);

        this.paypalGateway = new PayPalGateway(paypalConfig);
    }

    private void setConfig(String key, String defaultValue, HashMap<String, String> config){
        String processEndpoint = System.getenv().getOrDefault(key, defaultValue);
        config.put(key, processEndpoint);
    }

    @Override
    public String getGatewayType() {
        return "paypal";
    }

    @Override
    public PaymentGateway getGateWay(){
        return paypalGateway;
    }
}
