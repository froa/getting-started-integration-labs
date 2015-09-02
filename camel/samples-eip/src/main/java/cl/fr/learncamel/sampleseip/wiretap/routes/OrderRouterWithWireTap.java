package cl.fr.learncamel.sampleseip.wiretap.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * <a>Wire tap Pattern</a>
 * Created by froa on 9/2/15.
 */
public class OrderRouterWithWireTap extends SpringRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OrderRouterWithWireTap.class);

    @Value("#{'${file.dataInUri}'}")
    private String fileOrdersUri;

    @Value("#{'${queueCsvOrdersUri}'}")
    private String queueCsvOrdersUri;

    @Value("#{'${queueXmlOrdersUri}'}")
    private String queueXmlOrdersUri;

    @Value("#{'${queueBadOrdersUri}'}")
    private String queueBadOrdersUri;

    @Value("#{'${queueAuditOrdersUri}'}")
    private String queueAuditOrdersUri;

    @Value("#{'${queueContinuedOrderProcessingUri}'}")
    private String queueContinuedOrderProcessingUri;

    @Override
    public void configure() throws Exception {
        from("file:" + fileOrdersUri)
                .wireTap("jms:" + queueAuditOrdersUri)
                .choice()
                .when(header("CamelFileName").endsWith(".xml"))
                .to("jms:" + queueXmlOrdersUri)
                .when(header("CamelFileName").endsWith(".csv"))
                .to("jms:" + queueCsvOrdersUri)
                .otherwise()
                .to("jms:" + queueBadOrdersUri)
                        //route after Content based routing
                .to("jms:" + queueContinuedOrderProcessingUri);

    }
}
