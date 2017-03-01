package eu.dreamix.verticle;

import eu.dreamix.config.ApplicationConfiguration;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

/**
 * Vert.x verticle which initializes HTTP server and gives application information
 * Created by bdimitrov on 2/27/17.
 */
@Component
public class VertxServerVerticle extends AbstractVerticle {

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void start() throws Exception {
        super.start();

        vertx.createHttpServer().requestHandler(router()::accept).listen(applicationConfiguration.httpPort());
    }

    private Router router() {
        Router router = Router.router(vertx);

        router.route("/info").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();

            response.putHeader("Content-Type", "application/json");
            response.end(Json.encodePrettily(discoveryClient.getInstances(applicationConfiguration.applicationName())));
        });

        return router;
    }
}
