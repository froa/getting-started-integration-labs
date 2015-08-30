package cl.fr.learncamel.common.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by froa on 8/29/15.
 */
@Component
public class LoggerOrderProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(LoggerOrderProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Message msg = exchange.getIn();
        logger.debug("BODY:->" + msg.getBody(String.class));
        logger.debug("Received XML order: " + msg.getHeader("CamelFileName"));
    }
}
