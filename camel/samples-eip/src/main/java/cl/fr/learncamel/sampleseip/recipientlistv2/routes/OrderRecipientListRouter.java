package cl.fr.learncamel.sampleseip.recipientlistv2.routes;

import cl.fr.learncamel.sampleseip.recipientlist.Constants;
import cl.fr.learncamel.sampleseip.recipientlistv2.processors.OrdersRecipientListCalculatorBean;
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

    @Override
    public void configure() throws Exception {
        logger.debug("recipient list with annotation");
        from("jms:" + queueXmlOrdersUri)
                .bean(OrdersRecipientListCalculatorBean.class);
    }
}