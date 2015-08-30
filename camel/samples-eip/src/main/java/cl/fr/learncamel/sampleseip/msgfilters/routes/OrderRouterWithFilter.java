package cl.fr.learncamel.sampleseip.msgfilters.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by froa on 8/29/15.
 */
@Component
public class OrderRouterWithFilter extends SpringRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OrderRouterWithFilter.class);

    @Value("#{'${file.dataInUri}'}")
    private String fileOrdersUri;

    @Value("#{'${queueXmlOrdersUri}'}")
    private String queueXmlOrdersUri;

    @Value("#{'${queueContinuedOrderProcessingUri}'}")
    private String queueContinuedOrderProcessingUri;

    @Override
    public void configure() throws Exception {
        from("jms:" + queueXmlOrdersUri)
                //Filter Pattern
                .filter(xpath("/order[not(@test)]"))
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {

                        logger.debug("BODY_AS_STR:->" + exchange.getIn().getBody(String.class));

                        logger.debug("Received XML order: "
                                + exchange.getIn().getHeader("CamelFileName"));
                    } })
              .to("jms:" + queueContinuedOrderProcessingUri);

    }
}
