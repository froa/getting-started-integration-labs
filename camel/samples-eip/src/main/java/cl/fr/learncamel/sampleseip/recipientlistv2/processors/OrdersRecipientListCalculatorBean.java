package cl.fr.learncamel.sampleseip.recipientlistv2.processors;

import org.apache.camel.RecipientList;
import org.apache.camel.language.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by froa on 9/2/15.
 */
public class OrdersRecipientListCalculatorBean {

    private static final Logger logger = LoggerFactory.getLogger(OrdersRecipientListCalculatorBean.class);

    @Value("#{'${queueAccountingUri}'}")
    private String queueAccountingUri;

    @Value("#{'${queueProductionUri}'}")
    private String queueProductionUri;

    @Value("#{'${topTierCustomer}'}")
    private String topTierCustomer;

    @RecipientList
    public String[] route(@XPath("/order/@customer") String customer) {
        if (isTopTierCustomer(customer)) {
            return new String[] {"jms:" + queueAccountingUri, "jms:" + queueProductionUri};
        } else {
            return new String[] {"jms:" + queueAccountingUri};
        }
    }

    private boolean isTopTierCustomer(String customer) {
        return topTierCustomer.equalsIgnoreCase(customer);
    }

}
