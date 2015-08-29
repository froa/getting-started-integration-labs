package cl.fr.learncamel.camelkafkasample.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by froa on 8/29/15.
 *
 */
@Component
public class FtpToKafkaRoute extends SpringRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(FtpToKafkaRoute.class);

    @Value("#{'${ftp.ftpUriConsumer}'}")
    private String ftpUriConsumer;

    @Value("#{'${kafka.kafkaUriProducer}'}")
    private String kafkaUriProducer;

    @Override
    public void configure() throws Exception {
        from(ftpUriConsumer)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        logger.debug("--> file downloaded: "
                                + exchange.getIn().getHeader("CamelFileName"));

                    }
                }).convertBodyTo(String.class)
                .to(kafkaUriProducer);
    }
}