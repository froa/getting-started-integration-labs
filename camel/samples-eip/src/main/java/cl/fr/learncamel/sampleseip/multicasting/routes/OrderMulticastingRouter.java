package cl.fr.learncamel.sampleseip.multicasting.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by froa on 8/30/15.
 */
public class OrderMulticastingRouter extends SpringRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OrderMulticastingRouter.class);

    @Value("#{'${queueXmlOrdersUri}'}")
    private String queueXmlOrdersUri;

    @Value("#{'${queueAccountingUri}'}")
    private String queueAccountingUri;

    @Value("#{'${queueProductionUri}'}")
    private String queueProductionUri;

    @Value("#{'${threadsExecutor}'}")
    private int threadsExecutor;

    private ExecutorService executorService;

    @PostConstruct
    public void setupExecutor() {
        logger.trace("Setting up the threads on " + threadsExecutor);
        executorService = Executors.newFixedThreadPool(threadsExecutor);
    }

    @Override
    public void configure() throws Exception {
        logger.trace("sending the multicast->");
        from("jms:" + queueXmlOrdersUri)
                .multicast()
                .stopOnException()//optional
                .parallelProcessing()//optional action
                .executorService(executorService)//optional, default number is 10
                .to("jms:" + queueAccountingUri, "jms:" + queueProductionUri);

    }
}
