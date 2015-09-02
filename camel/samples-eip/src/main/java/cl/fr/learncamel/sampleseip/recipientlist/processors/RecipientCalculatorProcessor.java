package cl.fr.learncamel.sampleseip.recipientlist.processors;

import cl.fr.learncamel.sampleseip.recipientlist.Constants;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by froa on 9/2/15.
 */
@Component
public class RecipientCalculatorProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(RecipientCalculatorProcessor.class);

    @Value("#{'${queueAccountingUri}'}")
    private String queueAccountingUri;

    @Value("#{'${queueProductionUri}'}")
    private String queueProductionUri;

    @Value("#{'${topTierCustomer}'}")
    private String topTierCustomer;

    @Override
    public void process(Exchange exchange) throws Exception {
        String recipient = "jms:" + queueAccountingUri;
        Message msgIn = exchange.getIn();
        String customer = msgIn.getHeader(Constants.HEADER_FILTER_KEY, String.class);
        if (topTierCustomer.equalsIgnoreCase(customer)) {
            recipient = "jms:" + queueProductionUri;
        }
        msgIn.setHeader(Constants.HEADER_RECIPIENTS_KEY, recipient);
    }
}
