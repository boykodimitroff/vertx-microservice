package eu.dreamix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by bdimitrov on 3/1/17.
 */
@Configuration
public class ApplicationConfiguration {

    @Autowired
    private Environment environment;

    public String applicationName() {
        return environment.getProperty("spring.application.name");
    }

    public int httpPort() {
        return environment.getProperty("server.port", Integer.class);
    }
}
