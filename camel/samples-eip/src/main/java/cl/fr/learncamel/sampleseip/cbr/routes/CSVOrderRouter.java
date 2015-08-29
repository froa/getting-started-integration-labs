package cl.fr.learncamel.sampleseip.cbr.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by froa on 8/29/15.
 */
public class CSVOrderRouter extends SpringRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(CSVOrderRouter.class);

    @Value("#{'${queueCsvOrdersUri}'}")
    private String queueCsvOrdersUri;

    @Override
    public void configure() throws Exception {
        from("jms:" + queueCsvOrdersUri).process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                logger.trace("Received CSV order: "
                        + exchange.getIn().getHeader("CamelFileName"));
            }
        });
    }
}
