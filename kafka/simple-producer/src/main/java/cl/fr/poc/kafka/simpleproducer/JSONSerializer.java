package cl.fr.poc.kafka.simpleproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.serializer.Decoder;
import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by froa on 7/5/15.
 */
public class JSONSerializer<T> implements Encoder<T>, Decoder<T> {

    private static final Logger logger = LoggerFactory.getLogger(SimpleAppProducer.class);

    public JSONSerializer(VerifiableProperties verifiableProperties) {
        /* This constructor must be present for successful compile. */
        logger.debug("Constructor with: " + verifiableProperties);
    }

    /**
     * The object from the bytes.
     * In the case of Exception, it will return null value.
     */
    @Override
    public T fromBytes(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return (T) objectMapper.readValue(bytes, Map.class);
        } catch (IOException e) {
            logger.error(String.format("Json processing failed for object: %s", bytes.toString()), e);
        }
        return null;
    }

    /**
     * Object to bytes.
     * In the case of JsonProcessingException it will return the bytes from an empty string.
     */
    @Override
    public byte[] toBytes(T t) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(t).getBytes();
        } catch (JsonProcessingException e) {
            logger.error(String.format("Json processing failed for object: %s", t.getClass().getName()), e);
        }
        return "".getBytes();
    }
}
