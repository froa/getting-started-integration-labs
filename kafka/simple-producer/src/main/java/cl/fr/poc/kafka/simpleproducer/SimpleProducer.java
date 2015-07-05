package cl.fr.poc.kafka.simpleproducer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * Created by froa on 7/4/15.
 * @TODO Maybe we can setup the topic as part of the configurator class,
 * but it will depend on the strategy of the producers or capabilities of the kafka(p.e Set<String> topicsToPublish)
 */
public class SimpleProducer<T extends Serializable> {

    private static final Logger logger = LoggerFactory.getLogger(SimpleAppProducer.class);

    private Producer<String, MyMessage<T>> producer;

    private Properties props;

    public SimpleProducer(String brokerList) {
        props = new Properties();
        props.put("metadata.broker.list", brokerList);
        setupBaseProperties();
        configProducer();
    }

    public SimpleProducer() {
        props = new Properties();
        // Set the broker list for requesting metadata to find the lead broker
        props.put("metadata.broker.list", "localhost:9092");
        setupBaseProperties();
        configProducer();
    }

    /**
     * Constructor for IoC, or a client.
     * Take care for post change in properties, then it executes a copy.
     */
    public SimpleProducer(Map<String, String> properties) {
        props = new Properties();
        props.putAll(properties);
        configProducer();
    }

    private void setupBaseProperties() {
        // This specifies the serializer class for keys
        //props.put("serializer.class", "kafka.serializer.StringEncoder");
        //Custom serializer
        props.put("serializer.class", "cl.fr.poc.kafka.simpleproducer.JSONSerializer");
        // 1 means the producer receives an acknowledgment once the lead replica
        // has received the data. This option provides better durability as the
        // client waits until the server acknowledges the request as successful.
        props.put("request.required.acks", "1");
    }

    private void configProducer() {
        ProducerConfig producerConfig = new ProducerConfig(props);
        producer = new Producer<String, MyMessage<T>>(producerConfig);
        logger.debug("create simple producer: " + props);
    }


    public void publishMessage(String topic, T message) {
        String runtime = (new Date()).toString();
        MyMessage<T> myMessage = new MyMessage<T>(message, new Date());
        KeyedMessage<String, MyMessage<T>> data = new KeyedMessage<String, MyMessage<T>>(topic, myMessage);
        producer.send(data);
        producer.close();
    }
}
