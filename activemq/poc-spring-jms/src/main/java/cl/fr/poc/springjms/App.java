package cl.fr.poc.springjms;

import cl.fr.poc.springjms.service.Sender;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.Queue;

/**
 *
 * This is a simple demo to test de JMS on Active MQ.
 *
 * mvn exec:java -Dexec.mainClass="cl.fr.poc.springjms.App" -Dexec.args="mimsgee"
 *
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private static void validateInput(final String[] args) {
        logger.debug("args: ", args);
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
    }

    public static void main( String[] args ) {

        validateInput(args);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-ctx.xml");

        Sender sender = (Sender)ctx.getBean("activeMQSender");

        sender.send("send from App to default destination ->" + args[0]);

        Queue queue = (Queue) new ActiveMQQueue("queue.sampleQ3");
        sender.send(queue, "send from App to queue.sampleQ3 ->" + args[0]);

        ((ClassPathXmlApplicationContext)ctx).close();
    }
}
