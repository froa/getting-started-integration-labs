package cl.fr.learncamel.sampleseip.multicasting.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by froa on 8/30/15.
 */
public class OrderAccountingRouter extends SpringRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OrderAccountingRouter.class);

    @Value("#{'${queueAccountingUri}'}")
    private String queueAccountingUri;

    @Autowired
    @Qualifier(value = "loggerOrderProcessor")
    private Processor myProcessor;

    @Override
    public void configure() throws Exception {
        logger.trace("listening to accounting");
        from("jms:" + queueAccountingUri)
                .process(myProcessor);
    }
}
