package cl.fr.poc.kafka.simpleproducer;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    @BeforeClass
    public static void setUpClass() {
        logger.info("INFO");
        logger.debug("DEBUG");
        logger.error("ERROR");
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("AfterClass");
    }

    @Before
    public void setUp() {
        System.out.println("Before...");
    }

    @After
    public void tearDown() {
        System.out.println("After...");
    }

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp() {
        System.out.print("testApp()");
        Assert.assertTrue (true);
    }

    @Test
    public void testApp2() {
        System.out.println("testApp2()");
        Assert.assertEquals("True expected", "AA", "AA");
    }
}