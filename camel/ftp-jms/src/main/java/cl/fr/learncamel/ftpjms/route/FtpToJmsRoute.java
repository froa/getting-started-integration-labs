package cl.fr.learncamel.ftpjms.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by froa on 8/25/15.
 */
public class FtpToJmsRoute extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(FtpToJmsRoute.class);

    private String ftpsite;

    private String ftpuser;

    private String ftppwd;

    @Override
    public void configure() throws Exception {
        from("ftp://" + ftpsite + "?username=" + ftpuser + "&password=" + ftppwd)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        logger.debug("IoC------> file downloaded: "
                                + exchange.getIn().getHeader("CamelFileName"));
                    }
                })
                .to("jms:incomingOrders");
    }

    public void setFtpsite(String ftpsite) {
        this.ftpsite = ftpsite;
    }

    public void setFtpuser(String ftpuser) {
        this.ftpuser = ftpuser;
    }

    public void setFtppwd(String ftppwd) {
        this.ftppwd = ftppwd;
    }
}
