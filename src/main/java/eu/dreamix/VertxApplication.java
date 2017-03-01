package eu.dreamix;

import eu.dreamix.verticle.VertxServerVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;

@EnableDiscoveryClient
@SpringBootApplication
public class VertxApplication {

	@Autowired
	private VertxServerVerticle vertxServerVerticle;

	public static void main(String[] args) {
		SpringApplication.run(VertxApplication.class);
	}

	@PostConstruct
	public void deployServerVerticle() {
		Vertx.vertx().deployVerticle(vertxServerVerticle);
	}
}
