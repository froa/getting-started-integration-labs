package cl.fr.learncamel.camelkafkasample;

import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * mvn exec:java -Dexec.mainClass="cl.fr.learncamel.camelkafkasample.App"
 *
 */
public class App {

    public static void main( String[] args ) throws Exception {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("app-ctx.xml");
        SpringCamelContext camelCtx = (SpringCamelContext) appCtx.getBean("camelCtx");
        camelCtx.start();
        Thread.sleep(5000);
        camelCtx.stop();
    }
}
