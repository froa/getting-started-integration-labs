package cl.fr.learncamel.ftpjms.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by froa on 8/26/15.
 */
public class ListenerProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(ListenerProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Message msg = exchange.getIn();
        logger.debug("Listener------> file consumed: "
                + msg.getHeader("CamelFileName") + " ->" + msg.getHeader("JMSDestination"));

    }
}
