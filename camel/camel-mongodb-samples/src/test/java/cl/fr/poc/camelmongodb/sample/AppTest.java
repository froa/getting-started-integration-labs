package cl.fr.poc.camelmongodb.sample;

import cl.fr.poc.camelmongodb.sample.common.db.MongoAppConnector;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends CamelSpringTestSupport {

    private static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    private static final String APP_CTX_TEST_NAME = "classpath:app-ctx-test.xml";

    @Produce(uri = "direct:startFindAll")
    private ProducerTemplate producerFindAllTemplate;

    @Produce(uri = "direct:startFindOneByQuery")
    private ProducerTemplate producerFindByOneTemplate;

    @Produce(uri = "direct:startFindById")
    private ProducerTemplate producerFindByIdTemplate;

    //Autowired is not supported by CamelSpringTestSupport
    private MongoAppConnector mongoAppConnector;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        logger.info("@Before...");
        mongoAppConnector = applicationContext.getBean(MongoAppConnector.class);
        logger.info("@Before...");
        List<DBObject> objs = new ArrayList<DBObject>();
        for (int i = 0; i < 100; i++) {
            BasicDBObjectBuilder objBld = BasicDBObjectBuilder.start();
            objBld.add("_id", i);
            objBld.add("colsampleid", 100 + i);
            objs.add(objBld.get());
        }
        DBCollection samples = mongoAppConnector.getDatabase().getCollection("samples");
        samples.insert(objs);
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext(APP_CTX_TEST_NAME);
    }

    @After
    public void tearDown() {
        logger.info("@After...");
        DBCollection samples = mongoAppConnector.getDatabase().getCollection("samples");
        samples.drop();
    }

    /**
     *
     */
    @Test
    public void testFindAll() throws InterruptedException {
        MockEndpoint mock = getMockEndpoint("mock:resultFindAll");
        mock.expectedMessageCount(1);
        producerFindAllTemplate.sendBody("sample");
        assertMockEndpointsSatisfied();
    }

    @Test
    public void testFindAllRequestMode() throws InterruptedException {
        Object result = producerFindAllTemplate.requestBody("sample2");
        if (result != null && result instanceof List) {
            List<DBObject> resultDb = (List<DBObject>) result;
            int size = resultDb.size();
            Assert.assertTrue("Total objects back " + size,  size == 100);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testFindByOneQuery() throws InterruptedException {
        DBObject queryFirst = BasicDBObjectBuilder.start("colsampleid", 100).get();
        DBObject resultFirst = producerFindByOneTemplate.requestBody(queryFirst, DBObject.class);
        assertNotNull("doc found", resultFirst);
        assertNotNull("the doc: ", resultFirst.get("colsampleid"));

        DBObject queryLast = BasicDBObjectBuilder.start("colsampleid", 199).get();
        DBObject resultLast = producerFindByOneTemplate.requestBody(queryLast, DBObject.class);
        assertNotNull("doc found", resultLast);
        assertNotNull("id", resultLast.get("_id"));
        assertNotNull("the doc: ", resultLast.get("colsampleid"));
    }

    @Test
    public void testFindById() throws InterruptedException {
        DBObject result = producerFindByIdTemplate.requestBody(1, DBObject.class);
        assertNotNull("doc id found", result);
        assertNotNull("the id doc: ", (Integer)result.get("colsampleid") == 101);

    }

}
