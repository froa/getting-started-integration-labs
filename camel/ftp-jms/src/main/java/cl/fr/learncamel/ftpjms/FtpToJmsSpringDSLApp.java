package cl.fr.learncamel.ftpjms;

import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by froa on 8/25/15.
 *
 * mvn exec:java -Dexec.mainClass="cl.fr.learncamel.ftpjms.FtpToJmsSpringDSLApp"
 */
public class FtpToJmsSpringDSLApp {

    public static void main( String[] args ) throws Exception {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("app-ctx-springdsl.xml");
        SpringCamelContext camelCtx = (SpringCamelContext) appCtx.getBean("camelCtx");
        camelCtx.start();
        Thread.sleep(5000);
        camelCtx.stop();
    }
}
