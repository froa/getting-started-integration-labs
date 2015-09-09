package cl.fr.poc.camelmongodb.sample.common.db;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

/**
 * Created by froa on 9/3/15.
 */
@Component
public class MongoAppConnector {

    private static final Logger logger = LoggerFactory.getLogger(MongoAppConnector.class);

    private DB database;

    @Autowired
    public MongoAppConnector(@Value("#{'${mongodb.host}'}") String host,
                             @Value("#{'${mongodb.port}'}") int port,
                             @Value("#{'${mongodb.dbName}'}") String dbName) throws UnknownHostException {
        logger.info("Creating mongo connector with: " + host + ":" + port + "/" + dbName);
        MongoClient client = new MongoClient(host, port);
        database = client.getDB(dbName);
    }

    public DB getDatabase() {
        return database;
    }
}
