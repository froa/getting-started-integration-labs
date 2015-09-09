package cl.fr.poc.camelmongodb.sample.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by froa on 9/3/15.
 */
public class FindRouter extends SpringRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(FindRouter.class);

    @Value("#{'${startFindAllUri}'}")
    private String startFindAllUri;

    @Value("#{'${resultFindAllUri}'}")
    private String resultFindAllUri;

    @Value("#{'${startFindOneByQueryUri}'}")
    private String startFindOneByQueryUri;

    @Value("#{'${resultFindOneByQueryUri}'}")
    private String resultFindOneByQueryUri;

    @Value("#{'${startFindByIdUri}'}")
    private String startFindByIdUri;

    @Value("#{'${resultFindByIdUri}'}")
    private String resultFindByIdUri;

    @Override
    public void configure() throws Exception {

        from(startFindAllUri)
                .to("mongodb:mongoBean?database=poc-camel-mongo-db&collection=samples&operation=findAll")
                .to(resultFindAllUri);

        from(startFindOneByQueryUri)
                .to("mongodb:mongoBean?database=poc-camel-mongo-db&collection=samples&operation=findOneByQuery&dynamicity=true")
                .to(resultFindOneByQueryUri);

        from(startFindByIdUri)
                .to("mongodb:mongoBean?database=poc-camel-mongo-db&collection=samples&operation=findById&dynamicity=true")
                .to(resultFindByIdUri);
    }
}
