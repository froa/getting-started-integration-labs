package cl.fr.learncamel.common.routes;

import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by froa on 8/30/15.
 */
public class OrderProductionRouter extends SpringRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OrderProductionRouter.class);

    @Value("#{'${queueProductionUri}'}")
    private String queueProductionUri;

    @Autowired
    @Qualifier(value = "loggerOrderProcessor")
    private Processor myProcessor;

    @Override
    public void configure() throws Exception {
        logger.trace("listening to production");
        from("jms:" + queueProductionUri).process(myProcessor);
    }
}
