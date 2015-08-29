package cl.fr.learncamel.sampleseip.cbr.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by froa on 8/29/15.
 *
 * It takes the files from the src/data folder and put them in a queue based on the extension file type.
 *
 */
@Component
public class OrderRouter extends SpringRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OrderRouter.class);

    @Value("#{'${file.dataInUri}'}")
    private String fileOrdersUri;

    @Value("#{'${queueCsvOrdersUri}'}")
    private String queueCsvOrdersUri;

    @Value("#{'${queueXmlOrdersUri}'}")
    private String queueXmlOrdersUri;

    @Value("#{'${queueBadOrdersUri}'}")
    private String queueBadOrdersUri;

    @Value("#{'${queueContinuedOrderProcessingUri}'}")
    private String queueContinuedOrderProcessingUri;

    @Override
    public void configure() throws Exception {

        from("file:" + fileOrdersUri)
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
