package cl.fr.learncamel.ftpjms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.ConnectionFactory;

/**
 * Simple integration between FTP and JMS.
 *
 * Get file from ftp and send them to a queue.
 *
 * mvn exec:java -Dexec.mainClass="cl.fr.learncamel.ftpjms.FtpToJMSApp" -Dexec.args="bgd-lab ftpuser ftpuser"
 *
 */
public class FtpToJMSApp {

    private static final Logger logger = LoggerFactory.getLogger(FtpToJMSApp.class);

    private static void validateInput(final String[] args) {
        logger.debug("args: ", args);
        if (args == null || args.length != 3) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
    }

    public static void main( String[] args ) throws Exception {

        validateInput(args);
        final String ftpsite = args[0];
        final String ftpuser = args[1];
        final String ftppwd = args[2];


        CamelContext ctx = new DefaultCamelContext();

        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");
        ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));


        ctx.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("ftp://" + ftpsite +"?username=" + ftpuser + "&password=" + ftppwd)
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                logger.debug("------> file downloaded: "
                                        + exchange.getIn().getHeader("CamelFileName"));
                            }
                        })
                        .to("jms:incomingOrders");
            }
        });

        ctx.start();
        Thread.sleep(5000);
        ctx.stop();
    }
}
