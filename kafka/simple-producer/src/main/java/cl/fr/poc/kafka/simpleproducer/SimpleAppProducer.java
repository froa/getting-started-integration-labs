package cl.fr.poc.kafka.simpleproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple producer generated as a String message publisher.
 * You can pass the the next arguments by command line:
 * 0.- ip:port, ..., ip:port for the broker list
 * 1.- topicName
 * 2.- message
 *
 * For example, for a maven command:
 *
 * mvn exec:java -Dexec.mainClass="cl.fr.poc.kafka.simpleproducer.SimpleAppProducer" -Dexec.args="bgd-lab:9092 kafkatopic MySampleMessage!!"
 *
 */
public class SimpleAppProducer {

    private static final Logger logger = LoggerFactory.getLogger(SimpleAppProducer.class);

    private static int BROKER_LIST_INDEX = 0;

    private static int TOPIC_NAME_INDEX = 1;

    private static int MSG_INDEX = 2;

    /**
     *
     * @param args
     * @throws
     * @since
     */
    public static void main( String[] args ) throws Exception {
        for (int i = 0; i < args.length; i++) {
            logger.debug("arg[" + i + "]: " + args[i]);
        }
        if (args == null || args.length != 3) {
            throw new IllegalArgumentException("You must pass the 3 arguments");
        }
        SimpleProducer<String> producer = new SimpleProducer<String>(args[BROKER_LIST_INDEX]);
        logger.debug("Sending Msg!");
        producer.publishMessage(args[TOPIC_NAME_INDEX], args[MSG_INDEX]);
        logger.debug("End!");
        System.exit(0);
    }

}
