package cl.fr.learncamel.ftpjms.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by froa on 8/25/15.
 */
public class DownloadProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(DownloadProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.debug("DSL------> file downloaded: "
                + exchange.getIn().getHeader("CamelFileName"));
    }
}
