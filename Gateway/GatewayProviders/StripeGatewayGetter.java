package GatewayProviders;
import java.util.HashMap;
import java.util.Map;

import GatewayClasses.PaymentGateway;
import GatewayClasses.StripeGateway;

public class StripeGatewayGetter implements GatewayGetter{
    private StripeGateway stripeGateway;

    public StripeGatewayGetter() {
        HashMap<String, String> stripeConfig = new HashMap<>();

        this.setConfig("stripe_process_endpoint", "https://api.stripe.com/v1/charges/", stripeConfig);
        this.setConfig("stripe_refund_endpoint", "https://api.stripe.com/v1/refunds/", stripeConfig);
        this.setConfig("stripe_get_status_endpoint", "https://api.stripe.com/v1/status/", stripeConfig);

        this.stripeGateway = new StripeGateway(stripeConfig);
    }

    private void setConfig(String key, String defaultValue, HashMap<String, String> config){
        String processEndpoint = System.getenv().getOrDefault(key, defaultValue);
        config.put(key, processEndpoint);
    }
    
    @Override
    public String getGatewayType() {
        return "stripe";
    }

    public PaymentGateway getGateWay(){
        return stripeGateway;
    }
}
