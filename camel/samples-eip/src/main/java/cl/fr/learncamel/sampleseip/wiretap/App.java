package cl.fr.learncamel.sampleseip.wiretap;

import org.apache.camel.spring.SpringCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by froa on 8/30/15.
 * <a>Recipient List Pattern</a>
 *
 * mvn exec:java -Dexec.mainClass="cl.fr.learncamel.sampleseip.wiretap.App"
 *
 * mvn exec:java -Dexec.mainClass="cl.fr.learncamel.sampleseip.wiretap.App" -Dexec.args="spring"
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws Exception {

        String dsl = args.length == 1 && "spring".equals(args[0])?"spring":"java";
        logger.debug("Taking " + dsl + " configuration!");

        ApplicationContext appCtx = new ClassPathXmlApplicationContext("wiretap/app-ctx-" + dsl +"dsl.xml");
        SpringCamelContext camelCtx = (SpringCamelContext) appCtx.getBean("camelCtx");
        camelCtx.start();
        Thread.sleep(5000);
        camelCtx.stop();
    }
}
