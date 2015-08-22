package cl.fr.learncamel.filecopier;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * Sample File copier in camel, it is like a Hello world in camel.
 * You can pass the the next arguments by command line:
 * 0.- input data file
 * 1.- output data file
 * 2.- sleep before camel stop, it means the time of camel poll is running.
 * 3.- if this argument is equal true, then noop option will be true.
 *
 * For example, for a maven command:
 *
 * mvn exec:java -Dexec.mainClass="cl.fr.learncamel.filecopier.FileCopier" -Dexec.args="data/in data/out 10000 true"
 */
public class FileCopier {

    private static final Logger logger = LoggerFactory.getLogger(FileCopier.class);

    private static void validateInput(final String[] args) {
        logger.debug("args: ", args);
        if (args == null || args.length != 4 &&  Pattern.matches("\\d+", args[2]) ) {
            throw new IllegalArgumentException("Wrong number or type of arguments");
        }
    }

    /**
     * If the input directory does not exist, camel will create it.
     * If the output directory does not exist, camel will create it.
     * @param args
     * @throws Exception
     */
    public static void main( final String[] args ) throws Exception {

        validateInput(args);


        CamelContext camelCtx = new DefaultCamelContext();

        camelCtx.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                //noop option tells camel to leave the source as is.
                from("file:"+args[0]+"?noop="+("true".equalsIgnoreCase(args[3])))
                        .to("file:"+args[1]);

            }
        });

        camelCtx.start();
        Thread.sleep(Integer.parseInt(args[2]));
        camelCtx.stop();
    }
}
