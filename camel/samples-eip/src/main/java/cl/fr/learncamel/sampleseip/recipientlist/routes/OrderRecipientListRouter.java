package cl.fr.learncamel.sampleseip.recipientlist.routes;

import cl.fr.learncamel.sampleseip.recipientlist.Constants;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by froa on 9/2/15.
 */
public class OrderRecipientListRouter extends SpringRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OrderRecipientListRouter.class);

    @Value("#{'${queueXmlOrdersUri}'}")
    private String queueXmlOrdersUri;

    @Autowired
    @Qualifier(value = "recipientCalculatorProcessor")
    private Processor recipientCalculatorProcessor;

    @Override
    public void configure() throws Exception {
        from("jms:" + queueXmlOrdersUri)
                .setHeader(Constants.HEADER_FILTER_KEY, xpath("/order/@customer"))
                .process(recipientCalculatorProcessor)
        .recipientList(header(Constants.HEADER_RECIPIENTS_KEY));
    }
}
