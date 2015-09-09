package cl.fr.poc.camelmongodb.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        String dsl = args.length == 1 && "spring".equals(args[0])?"spring":"java";
        logger.debug("Taking " + dsl + " configuration!");

        ApplicationContext appCtx = new ClassPathXmlApplicationContext("app-ctx.xml");



    }
}
