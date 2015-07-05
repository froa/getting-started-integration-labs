package cl.fr.poc.kafka.simpleproducer;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by froa on 7/4/15.
 */
public class MyMessage <M extends Serializable> implements Serializable {

    private M message;

    private Date publishDate;

    public MyMessage(M message, Date publishDate) {
        this.message = message;
        this.publishDate = publishDate;
    }

    public M getMessage() {
        return message;
    }

    public Date getPublishDate() {
        return publishDate;
    }
}
